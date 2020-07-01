package com.softcrud.TestPipeLine.Pojos.GeoShapes;

import java.util.List;

public class GeoShapeList {
    public String alcaldia;
    public List<String> geoShapes;

    public GeoShapeList(String alcaldia, List<String> geoShapes) {
        this.alcaldia = alcaldia;
        this.geoShapes = geoShapes;
    }
}
