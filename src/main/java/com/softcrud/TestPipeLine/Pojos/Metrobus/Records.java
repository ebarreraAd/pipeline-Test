package com.softcrud.TestPipeLine.Pojos.Metrobus;

public class Records {
    public String datasetid;
    public String recordid;
    public Fields fields;
    public Geometry geometry;
    public String record_timestamp;

    public Records(String datasetid, String recordid, Fields fields, Geometry geometry, String record_timestamp) {
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.fields = fields;
        this.geometry = geometry;
        this.record_timestamp = record_timestamp;
    }
}
