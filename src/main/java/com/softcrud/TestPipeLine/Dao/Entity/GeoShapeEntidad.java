package com.softcrud.TestPipeLine.Dao.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Mapeo de la tabla
 */
@Getter
@Setter
@Entity
@Table(name = "t_geoshape")
public class GeoShapeEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idGeo;

    @Column(name = "latitud")
    public String latitud;

    @Column(name = "longitud")
    public String longitud;

    @Column(name = "recordid")
    public String recordId;

    @Column(name = "lat_long")
    public String latLong;
}
