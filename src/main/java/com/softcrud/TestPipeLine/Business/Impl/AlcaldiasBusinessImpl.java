package com.softcrud.TestPipeLine.Business.Impl;

import com.google.gson.Gson;
import com.softcrud.TestPipeLine.Business.Interfaces.AlcaldiasBusiness;
import com.softcrud.TestPipeLine.Dao.Entity.AlcaldiasEntity;
import com.softcrud.TestPipeLine.Dao.Entity.GeoShapeEntity;
import com.softcrud.TestPipeLine.Dao.Interfaces.AlcaldiasDao;
import com.softcrud.TestPipeLine.Pojos.Alcaldias.Alcaldia;
import com.softcrud.TestPipeLine.Pojos.Alcaldias.Alcaldias;
import com.softcrud.TestPipeLine.Utils.ConstantsText;
import com.softcrud.TestPipeLine.Ws.HeaderGeneric;
import com.softcrud.TestPipeLine.Ws.Response.Alcaldias.GetAlcaldiasResponse;
import com.softcrud.TestPipeLine.Ws.Response.HeaderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AlcaldiasBusinessImpl implements AlcaldiasBusiness {

    @Autowired
    private AlcaldiasDao alcaldiasDao;

    /**
     * Consultar el api de las alcaldias de la CDMX
     * @return
     */
    @Override
    public ResponseEntity<HeaderResponse> saveAlcaldias() {
        Alcaldias alcaldias = null;
        Gson gsonObj = new Gson();
        try {
            URL url = new URL("https://datos.cdmx.gob.mx/api/records/1.0/search/?dataset=limite-de-las-alcaldias&q=&rows=16&facet=nomgeo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            alcaldias = gsonObj.fromJson(br, Alcaldias.class);
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return saveAlcaldiasDb(alcaldias);
    }

    /**
     * Metodo para obtener las alcaldias
     * @return
     */
    @Override
    public ResponseEntity<GetAlcaldiasResponse> getAlcaldias() {
        String msg;
        GetAlcaldiasResponse response;
        Iterable<AlcaldiasEntity> allAlcaldias = alcaldiasDao.findAll();
        List<Alcaldia> alcaldias = new ArrayList<>();
        allAlcaldias.forEach(alcaldiasEntity -> {
            alcaldias.add(new Alcaldia(alcaldiasEntity.nomGeo));
        });
        msg = ConstantsText.ALCALDIAS_GET;
        response = new GetAlcaldiasResponse(new HeaderGeneric(ConstantsText.SUCCESS, HttpStatus.OK.value(), msg), alcaldias);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Metodo para guardar las alcaldías en la base de datos
     * @return
     */
    public ResponseEntity<HeaderResponse> saveAlcaldiasDb(Alcaldias alcaldias) {
        String msg;
        HeaderResponse response;
        alcaldias.records.forEach(recordsAlcaldias -> {
            AlcaldiasEntity alcaldiasEntity = new AlcaldiasEntity();
            alcaldiasEntity.cveEnt = recordsAlcaldias.fields.cve_ent;
            alcaldiasEntity.cveGeo = recordsAlcaldias.fields.cvegeo;
            alcaldiasEntity.cveMun = recordsAlcaldias.fields.cve_mun;
            alcaldiasEntity.datasetId = recordsAlcaldias.datasetid;
            alcaldiasEntity.gid = recordsAlcaldias.fields.gid;
            alcaldiasEntity.nomGeo = recordsAlcaldias.fields.nomgeo;
            alcaldiasEntity.recordId = recordsAlcaldias.recordid;
            alcaldiasEntity.recordTimeStamp = recordsAlcaldias.record_timestamp;
            List coordinates = recordsAlcaldias.fields.geo_shape.coordinates;
            alcaldiasEntity.geoShapeEntityList = getListCoordinates(coordinates, recordsAlcaldias.recordid, alcaldiasEntity);
            alcaldiasDao.save(alcaldiasEntity);
        });
        msg = ConstantsText.PERIODICALLY_SAVE;
        response = new HeaderResponse(new HeaderGeneric(ConstantsText.SUCCESS, HttpStatus.OK.value(), msg));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Metodo para obtener las coordenadas de cada alcaldia
     * Para poder guardarlo en BD
     * @param polygon
     * @param recordid
     * @param alcaldiasEntity
     * @return
     */
    private List<GeoShapeEntity> getListCoordinates(List polygon, String recordid, AlcaldiasEntity alcaldiasEntity) {
        List<GeoShapeEntity> shapeEntityList = new ArrayList<>();
        Object coord = Arrays.asList(polygon.get(0)).get(0);
        ArrayList coordinatesList;
        coordinatesList = (ArrayList) coord;
        coordinatesList.forEach(coordinates -> {
            GeoShapeEntity geoShapeEntity = new GeoShapeEntity();
            geoShapeEntity.latitud = ((ArrayList) coordinates).get(1).toString();
            geoShapeEntity.longitud = ((ArrayList) coordinates).get(0).toString();
            geoShapeEntity.recordId = recordid;
            geoShapeEntity.latLong = ((ArrayList) coordinates).get(1).toString() + " " + ((ArrayList) coordinates).get(0).toString();
            geoShapeEntity.alcaldiasEntity = alcaldiasEntity;
            shapeEntityList.add(geoShapeEntity);
        });

        return shapeEntityList;
    }

}
