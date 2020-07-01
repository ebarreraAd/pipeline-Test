package com.softcrud.TestPipeLine.Pojos.Alcaldias;

import java.util.List;

public class ParametersAlcaldias {
    public String dataset;
    public String timezone;
    public String rows;
    public String format;
    public List<String> facet;

    public ParametersAlcaldias(String dataset, String timezone, String rows, String format, List<String> facet) {
        this.dataset = dataset;
        this.timezone = timezone;
        this.rows = rows;
        this.format = format;
        this.facet = facet;
    }
}
