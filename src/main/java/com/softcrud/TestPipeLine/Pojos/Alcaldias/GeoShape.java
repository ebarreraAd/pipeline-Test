package com.softcrud.TestPipeLine.Pojos.Alcaldias;

import java.util.List;

public class GeoShape {
    public String type;
    public List coordinates;

    public GeoShape(String type, List coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
