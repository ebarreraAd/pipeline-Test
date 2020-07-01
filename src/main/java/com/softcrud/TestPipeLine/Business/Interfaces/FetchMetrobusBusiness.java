package com.softcrud.TestPipeLine.Business.Interfaces;

import com.softcrud.TestPipeLine.Ws.Response.Metrobus.GetUnidadesResponse;
import com.softcrud.TestPipeLine.Ws.Response.Metrobus.MetrobusResponse;
import org.springframework.http.ResponseEntity;

/**
 * Interfaz con los metodos a implementar
 */
public interface FetchMetrobusBusiness {
    ResponseEntity<MetrobusResponse> periodicallyConsultDataMetrobus();

    ResponseEntity<GetUnidadesResponse> getUnidades();

    ResponseEntity<GetUnidadesResponse> getHistoryUnidadesById(String idVehicle);

    ResponseEntity<GetUnidadesResponse> getUnidadesByAlcaldía(String alcaldia);
}
