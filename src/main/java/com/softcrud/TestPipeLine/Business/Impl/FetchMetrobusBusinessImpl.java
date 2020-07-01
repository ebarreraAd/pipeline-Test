package com.softcrud.TestPipeLine.Business.Impl;

import com.google.gson.Gson;
import com.softcrud.TestPipeLine.Business.Interfaces.FetchMetrobusBusiness;
import com.softcrud.TestPipeLine.Dao.Entity.AlcaldiasEntity;
import com.softcrud.TestPipeLine.Dao.Entity.GeoShapeEntidad;
import com.softcrud.TestPipeLine.Dao.Entity.MetrobusEntity;
import com.softcrud.TestPipeLine.Dao.Interfaces.AlcaldiasDao;
import com.softcrud.TestPipeLine.Dao.Interfaces.GeoDao;
import com.softcrud.TestPipeLine.Dao.Interfaces.MetrobusDao;
import com.softcrud.TestPipeLine.Pojos.GeoShapes.GeoShapeList;
import com.softcrud.TestPipeLine.Pojos.Metrobus.MetroBus;
import com.softcrud.TestPipeLine.Pojos.Metrobus.Unidades;
import com.softcrud.TestPipeLine.Utils.ConstantsText;
import com.softcrud.TestPipeLine.Ws.HeaderGeneric;
import com.softcrud.TestPipeLine.Ws.Response.Metrobus.GetUnidadesResponse;
import com.softcrud.TestPipeLine.Ws.Response.Metrobus.MetrobusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class FetchMetrobusBusinessImpl implements FetchMetrobusBusiness {

    @Autowired
    private MetrobusDao metrobusDao;

    @Autowired
    private GeoDao geoDao;

    @Autowired
    private AlcaldiasDao alcaldiasDao;

    public static double PI = 3.14159265;
    public static double TWOPI = 2 * PI;

    /**
     * Metodo para consultar los datos del metrobus
     * @return
     */
    @Async
    @Override
    public ResponseEntity<MetrobusResponse> periodicallyConsultDataMetrobus() {
        MetroBus metroBus = null;
        Gson gsonObj = new Gson();
        try {
            URL url = new URL("https://datos.cdmx.gob.mx/api/records/1.0/search/?dataset=prueba_fetchdata_metrobus&q=&rows=500");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            metroBus = gsonObj.fromJson(br, MetroBus.class);
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return saveDataMetrobus(metroBus);
    }

    /**
     * Metodo para obtener las unidades disponibles
     * @return
     */
    @Override
    public ResponseEntity<GetUnidadesResponse> getUnidades() {
        String fecha = convertDate();
        return extractDataUnidades(metrobusDao.getMetrobusesActives(fecha));
    }

    /**
     * Metodo para extraer los datos de cada unidad y convertirlas para regresar el json
     * @param metrobuses
     * @return
     */
    private ResponseEntity<GetUnidadesResponse> extractDataUnidades(List<MetrobusEntity> metrobuses) {
        String msg;
        GetUnidadesResponse response;
        List<Unidades> unidades = new ArrayList<>();
        metrobuses.forEach(metrobusEntity -> unidades.add(new Unidades(metrobusEntity.vehicleId, metrobusEntity.vehicleCurrentStatus, metrobusEntity.dateUpdated, metrobusEntity.alcaldia)));
        msg = ConstantsText.UNIDADES_GET;
        response = new GetUnidadesResponse(new HeaderGeneric(ConstantsText.SUCCESS, HttpStatus.OK.value(), msg), unidades);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Metodo para obtener el historial de unidades por id
     * @param idVehicle
     * @return
     */
    @Override
    public ResponseEntity<GetUnidadesResponse> getHistoryUnidadesById(String idVehicle) {
        return extractDataUnidades(metrobusDao.findByVehicleId(idVehicle));
    }

    /**
     * Metodo para obtener las unidades por alcaldia
     * @param alcaldia
     * @return
     */
    @Override
    public ResponseEntity<GetUnidadesResponse> getUnidadesByAlcaldía(String alcaldia) {
        return extractDataUnidades(metrobusDao.getByAlcaldia(alcaldia));
    }

    /**
     * Metodo para convertir una fecha en string
     * @return
     */
    public String convertDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * Metodo para guardar los registros consultados del metrobus
     * @param metroBus
     * @return
     */
    @Async
    private ResponseEntity<MetrobusResponse> saveDataMetrobus(MetroBus metroBus) {
        String msg;
        MetrobusResponse response;
        List<GeoShapeList> geoShape = getGeoShape();
        metroBus.records.forEach(records -> {
            MetrobusEntity metrobusEntity = new MetrobusEntity();
            metrobusEntity.datasetId = records.datasetid;
            metrobusEntity.recordId = records.recordid;
            metrobusEntity.vehicleId = records.fields.vehicle_id;
            metrobusEntity.tripStartDate = records.fields.trip_start_date;
            metrobusEntity.dateUpdated = records.fields.date_updated;
            metrobusEntity.positionLongitude = records.fields.position_longitude;
            metrobusEntity.tripScheduleRelationship = records.fields.trip_schedule_relationship;
            metrobusEntity.positionSpeed = records.fields.position_speed;
            metrobusEntity.positionLatitude = records.fields.position_latitude;
            metrobusEntity.tripRouteId = records.fields.trip_route_id;
            metrobusEntity.vehicleLabel = records.fields.vehicle_label;
            metrobusEntity.positionOdometer = records.fields.position_odometer;
            metrobusEntity.tripId = records.fields.trip_id;
            metrobusEntity.vehicleCurrentStatus = records.fields.vehicle_current_status;
            metrobusEntity.georaphicPointLat = records.fields.geographic_point.get(0);
            metrobusEntity.geographicPointLon = records.fields.geographic_point.get(1);
            metrobusEntity.type = records.geometry.type;
            metrobusEntity.coordinatesLat = records.geometry.coordinates.get(1);
            metrobusEntity.coordinatesLon = records.geometry.coordinates.get(0);
            metrobusEntity.recordTimeStamp = records.record_timestamp;
            metrobusEntity.dateAdd = new Date();
            String alcaldia = getAlcaldia(geoShape, records.geometry.coordinates.get(1), records.geometry.coordinates.get(0));
            metrobusEntity.alcaldia = alcaldia;
            metrobusDao.save(metrobusEntity);
        });
        msg = ConstantsText.PERIODICALLY_SAVE;
        response = new MetrobusResponse(new HeaderGeneric(ConstantsText.SUCCESS, HttpStatus.OK.value(), msg), null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Metodo para obtener la alcaldía en que se encuentra cada unidad, mediante
     * las coordenadas del poligono
     * @param geoShapeLists
     * @param pointLat
     * @param pointLon
     * @return
     */
    @Async
    private String getAlcaldia(List<GeoShapeList> geoShapeLists, String pointLat, String pointLon) {
        String alcaldia = "";
        for (GeoShapeList geoShapeList : geoShapeLists) {
            boolean convert = convert(geoShapeList.geoShapes, pointLat, pointLon);
            if (convert) {
                alcaldia = geoShapeList.alcaldia;
                break;
            }

        }
        return alcaldia;
    }

    /**
     * Metodo para convertir las coordenadas en Tipo Double
     * @param shapes
     * @param pointLat
     * @param pointLon
     * @return
     */
    private boolean convert(List<String> shapes, String pointLat, String pointLon) {
        //Convert the strings to doubles.
        ArrayList<Double> lat_array = new ArrayList<Double>();
        ArrayList<Double> long_array = new ArrayList<Double>();
        for (String s : shapes) {
            lat_array.add(Double.parseDouble(s.split(" ")[0]));
            long_array.add(Double.parseDouble(s.split(" ")[1]));
        }
        return coordinate_is_inside_polygon(
                Double.parseDouble(pointLat), Double.parseDouble(pointLon),
                lat_array, long_array);
    }

    /**
     * Metodo para verificar mediante un algoritmo si las coordenadas pertenecen a un poligono
     * @param latitude
     * @param longitude
     * @param lat_array
     * @param long_array
     * @return
     */
    public static boolean coordinate_is_inside_polygon(
            double latitude, double longitude,
            ArrayList<Double> lat_array, ArrayList<Double> long_array) {
        int i;
        double angle = 0;
        double point1_lat;
        double point1_long;
        double point2_lat;
        double point2_long;
        int n = lat_array.size();

        for (i = 0; i < n; i++) {
            point1_lat = lat_array.get(i) - latitude;
            point1_long = long_array.get(i) - longitude;
            point2_lat = lat_array.get((i + 1) % n) - latitude;
            //you should have paid more attention in high school geometry.
            point2_long = long_array.get((i + 1) % n) - longitude;
            angle += Angle2D(point1_lat, point1_long, point2_lat, point2_long);
        }

        if (Math.abs(angle) < PI)
            return false;
        else
            return true;
    }

    public static double Angle2D(double y1, double x1, double y2, double x2) {
        double dtheta, theta1, theta2;

        theta1 = Math.atan2(y1, x1);
        theta2 = Math.atan2(y2, x2);
        dtheta = theta2 - theta1;
        while (dtheta > PI)
            dtheta -= TWOPI;
        while (dtheta < -PI)
            dtheta += TWOPI;

        return (dtheta);
    }


    /**
     * Metodo para obtener las coordenadas del poligono de cada alcaldia
     * @return
     */
    @Async
    private List<GeoShapeList> getGeoShape() {
        Iterable<AlcaldiasEntity> alcaldias = alcaldiasDao.findAll();
        List<GeoShapeList> geoShapeLists = new ArrayList<>();
        alcaldias.forEach(alcaldiasEntity -> {
            List<GeoShapeEntidad> geoShapeByRecordId = geoDao.getGeoShapeByRecordId(alcaldiasEntity.recordId);
            List<String> geoShapes = new ArrayList<>();
            geoShapeByRecordId.forEach(geoShapeEntity -> {
                geoShapes.add(geoShapeEntity.latLong);
            });
            geoShapeLists.add(new GeoShapeList(alcaldiasEntity.nomGeo, geoShapes));
        });
        return geoShapeLists;
    }
}
