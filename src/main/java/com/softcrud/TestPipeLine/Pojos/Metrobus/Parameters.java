package com.softcrud.TestPipeLine.Pojos.Metrobus;

public class Parameters {
     public  String dataset;
     public String timezone;
     public String rows;
     public String format;

    public Parameters(String dataset, String timezone, String rows, String format) {
        this.dataset = dataset;
        this.timezone = timezone;
        this.rows = rows;
        this.format = format;
    }
}
