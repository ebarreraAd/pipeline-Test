package com.softcrud.TestPipeLine.Dao.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Mapeo de la tabla
 */
@Data
@Entity
@Table(name = "t_alcaldias")
public class AlcaldiasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idAlcaldia;

    @Column(name = "datasetid")
    public String datasetId;

    @Column(name = "recordid")
    public String recordId;

    @Column(name = "cve_mun")
    public String cveMun;

    @Column(name = "cvegeo")
    public String cveGeo;

    @Column(name = "gid")
    public String gid;

    @Column(name = "cve_ent")
    public String cveEnt;

    @Column(name = "nomgeo")
    public String nomGeo;

    @Column(name = "record_timestamp")
    public String recordTimeStamp;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "alcaldiasEntity")
    @JsonManagedReference
    public List<GeoShapeEntity> geoShapeEntityList;
}
