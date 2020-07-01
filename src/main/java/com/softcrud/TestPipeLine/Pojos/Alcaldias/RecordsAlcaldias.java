package com.softcrud.TestPipeLine.Pojos.Alcaldias;

import com.softcrud.TestPipeLine.Pojos.Metrobus.Geometry;

public class RecordsAlcaldias {
    public String datasetid;
    public String recordid;
    public FieldsAlcaldias fields;
    public Geometry geometry;
    public String record_timestamp;

    public RecordsAlcaldias(String datasetid, String recordid, FieldsAlcaldias fields, Geometry geometry, String record_timestamp) {
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.fields = fields;
        this.geometry = geometry;
        this.record_timestamp = record_timestamp;
    }
}
