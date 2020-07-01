package com.softcrud.TestPipeLine.Pojos.Metrobus;

import java.util.List;

public class MetroBus {
    public String nhits;
    public Parameters parameters;
    public List<Records> records;

    public MetroBus(String nhits, Parameters parameters, List<Records> records) {
        this.nhits = nhits;
        this.parameters = parameters;
        this.records = records;
    }
}
