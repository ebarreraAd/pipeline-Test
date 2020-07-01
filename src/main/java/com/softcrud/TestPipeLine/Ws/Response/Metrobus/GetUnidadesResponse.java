package com.softcrud.TestPipeLine.Ws.Response.Metrobus;

import com.softcrud.TestPipeLine.Pojos.Metrobus.Unidades;
import com.softcrud.TestPipeLine.Ws.HeaderGeneric;

import java.util.List;

public class GetUnidadesResponse {
    public HeaderGeneric header;
    public List<Unidades> data;

    public GetUnidadesResponse(HeaderGeneric header, List<Unidades> data) {
        this.header = header;
        this.data = data;
    }
}
