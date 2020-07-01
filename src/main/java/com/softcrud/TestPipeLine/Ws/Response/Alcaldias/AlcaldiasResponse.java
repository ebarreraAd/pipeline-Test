package com.softcrud.TestPipeLine.Ws.Response.Alcaldias;

import com.softcrud.TestPipeLine.Pojos.Alcaldias.Alcaldias;
import com.softcrud.TestPipeLine.Ws.HeaderGeneric;

public class AlcaldiasResponse {
    public HeaderGeneric header;
    public Alcaldias data;

    public AlcaldiasResponse(HeaderGeneric header, Alcaldias data) {
        this.header = header;
        this.data = data;
    }
}
