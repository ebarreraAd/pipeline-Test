package com.softcrud.TestPipeLine.Pojos.Metrobus;

import java.util.List;

public class Geometry {
    public  String type;
    public List<String> coordinates;

    public Geometry(String type, List<String> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
