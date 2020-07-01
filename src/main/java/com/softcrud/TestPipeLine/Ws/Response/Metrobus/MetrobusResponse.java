package com.softcrud.TestPipeLine.Ws.Response.Metrobus;

import com.softcrud.TestPipeLine.Pojos.Metrobus.MetroBus;
import com.softcrud.TestPipeLine.Ws.HeaderGeneric;

public class MetrobusResponse {
    public HeaderGeneric header;
    public MetroBus data;

    public MetrobusResponse(HeaderGeneric header, MetroBus data) {
        this.header = header;
        this.data = data;
    }
}
