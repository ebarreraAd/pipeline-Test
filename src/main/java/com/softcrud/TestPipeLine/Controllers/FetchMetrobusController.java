package com.softcrud.TestPipeLine.Controllers;

import com.softcrud.TestPipeLine.Business.Interfaces.FetchMetrobusBusiness;
import com.softcrud.TestPipeLine.Utils.ConstantsText;
import com.softcrud.TestPipeLine.Ws.Response.Metrobus.GetUnidadesResponse;
import com.softcrud.TestPipeLine.Ws.Response.Metrobus.MetrobusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin
@RestController
@RequestMapping(ConstantsText.API_V1 + "metrobus")
public class FetchMetrobusController {
    private static final Logger LOG = LoggerFactory.getLogger(FetchMetrobusController.class);

    @Autowired
    private FetchMetrobusBusiness fetchMetrobusBusiness;
    //Cada 5 segundos se invoca
    //@Scheduled(cron = "*/5 * * * * *", zone="America/Mexico_City")

    /**
     * ESte metodo sirve para consultar periodicamente cada hora despues de 10 sgundos el api del metrobus.
     * Insertar los registros consultados en la base de datos
     */
    @Scheduled(cron = "10 0 0-23 * * * ", zone="America/Mexico_City")
    @GetMapping(value = "/periodically")
    ResponseEntity<MetrobusResponse> fetchData() {
        LOG.info("Endpoint entry GET Method: /api/v1/metrobus/add Request: " + " at " + new Date());
        return fetchMetrobusBusiness.periodicallyConsultDataMetrobus();
    }

    /**
     * Este endpoint sirve para obtener las unidades disponibles en la hora actual
     * @return
     */
    @GetMapping(value = "/getUnidades")
    ResponseEntity<GetUnidadesResponse> getUnidades() {
        LOG.info("Endpoint entry GET Method: /api/v1/getUnidades/add Request: " + " at " + new Date());
        return fetchMetrobusBusiness.getUnidades();
    }

    /**
     * Endpoint para obtener el historial de ubicaciones de una unidad mediante su id
     * @param vehicleId
     * @return
     */
    @GetMapping(value = "/getHistory")
    ResponseEntity<GetUnidadesResponse> getHistoryUnidades(@RequestParam(value = "vehicleId") String vehicleId) {
        LOG.info("Endpoint entry GET Method: /api/v1/getHistory Request: " + " at " + new Date());
        return fetchMetrobusBusiness.getHistoryUnidadesById(vehicleId);
    }

    /**
     * Endpoint para obtener las unidades por alcaldia
     * @param alcaldia
     * @return
     */
    @GetMapping(value = "/getByAlcaldia")
    ResponseEntity<GetUnidadesResponse> getUnidadesByAlcaldia(@RequestParam(value = "alcaldia") String alcaldia) {
        LOG.info("Endpoint entry GET Method: /api/v1/getByAlcaldia Request: " + " at " + new Date());
        return fetchMetrobusBusiness.getUnidadesByAlcaldía(alcaldia);
    }
}
