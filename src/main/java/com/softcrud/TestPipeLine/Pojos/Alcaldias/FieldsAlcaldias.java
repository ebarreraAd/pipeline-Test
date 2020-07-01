package com.softcrud.TestPipeLine.Pojos.Alcaldias;

import java.util.List;

public class FieldsAlcaldias {
    public List<String> geo_point_2d;
    public String cve_mun;
    public String cvegeo;
    public String gid;
    public GeoShape geo_shape;
    public String cve_ent;
    public String nomgeo;

    public FieldsAlcaldias(List<String> geo_point_2d, String cve_mun, String cvegeo, String gid, GeoShape geo_shape, String cve_ent, String nomgeo) {
        this.geo_point_2d = geo_point_2d;
        this.cve_mun = cve_mun;
        this.cvegeo = cvegeo;
        this.gid = gid;
        this.geo_shape = geo_shape;
        this.cve_ent = cve_ent;
        this.nomgeo = nomgeo;
    }
}
