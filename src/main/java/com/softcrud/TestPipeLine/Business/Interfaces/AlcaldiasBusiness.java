package com.softcrud.TestPipeLine.Business.Interfaces;

import com.softcrud.TestPipeLine.Ws.Response.Alcaldias.GetAlcaldiasResponse;
import com.softcrud.TestPipeLine.Ws.Response.HeaderResponse;
import org.springframework.http.ResponseEntity;

/**
 * Interfaz con los metodos a implementar
 */
public interface AlcaldiasBusiness {
    ResponseEntity<HeaderResponse> saveAlcaldias();

    ResponseEntity<GetAlcaldiasResponse> getAlcaldias();
}
