package com.softcrud.TestPipeLine.Controllers;

import com.softcrud.TestPipeLine.Business.Interfaces.AlcaldiasBusiness;
import com.softcrud.TestPipeLine.Utils.ConstantsText;
import com.softcrud.TestPipeLine.Ws.Response.Alcaldias.GetAlcaldiasResponse;
import com.softcrud.TestPipeLine.Ws.Response.HeaderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(ConstantsText.API_V1 + "alcaldias")
public class AlcaldiasController {
    private static final Logger LOG = LoggerFactory.getLogger(AlcaldiasController.class);

    @Autowired
    private AlcaldiasBusiness alcaldiasBusiness;

    /**
     * Endpoint para guardar las alcaldias consultadas mediante el api de la CDMX
     * @return
     */
    @PostMapping(value = "/add")
    ResponseEntity<HeaderResponse> saveAlcaldias() {
        LOG.info("Endpoint entry POST Method: /api/v1/alcaldias/add Request: " + " at " + new Date());
        return alcaldiasBusiness.saveAlcaldias();
    }

    /**
     * Endpoint para obtener las alcaldias
     * @return
     */
    @GetMapping(value = "/getAlcaldias")
    ResponseEntity<GetAlcaldiasResponse> getAlcaldias() {
        LOG.info("Endpoint entry GET Method: /api/v1/getAlcaldias/add Request: " + " at " + new Date());
        return alcaldiasBusiness.getAlcaldias();
    }

}
