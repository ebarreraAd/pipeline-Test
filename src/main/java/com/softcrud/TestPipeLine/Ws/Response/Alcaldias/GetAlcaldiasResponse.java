package com.softcrud.TestPipeLine.Ws.Response.Alcaldias;

import com.softcrud.TestPipeLine.Dao.Entity.AlcaldiasEntity;
import com.softcrud.TestPipeLine.Pojos.Alcaldias.Alcaldia;
import com.softcrud.TestPipeLine.Ws.HeaderGeneric;

import java.util.List;

public class GetAlcaldiasResponse {
    public HeaderGeneric header;
    public List<Alcaldia> data;

    public GetAlcaldiasResponse(HeaderGeneric header, List<Alcaldia> data) {
        this.header = header;
        this.data = data;
    }
}
