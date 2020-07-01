package com.softcrud.TestPipeLine.Dao.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Mapeo de la tabla
 */
@Getter
@Setter
@Entity
@Table(name = "t_geoshape")
public class GeoShapeEntity {

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

    @NotNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "recordid", insertable = false, updatable = false)
    public AlcaldiasEntity alcaldiasEntity;
}
