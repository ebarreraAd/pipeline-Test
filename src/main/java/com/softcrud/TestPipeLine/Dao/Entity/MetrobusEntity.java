package com.softcrud.TestPipeLine.Dao.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * Mapeo de la tabla
 */
@Data
@Entity
@Table(name = "t_metrobus")
public class MetrobusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idData;

    @Column(name = "datasetid")
    public String datasetId;

    @Column(name = "recordid")
    public String recordId;

    @Column(name = "vehicle_id")
    public String vehicleId;

    @Column(name = "trip_start_date")
    public String tripStartDate;

    @Column(name = "date_updated")
    public String dateUpdated;

    @Column(name = "position_longitude")
    public String positionLongitude;

    @Column(name = "trip_schedule_relationship")
    public String tripScheduleRelationship;

    @Column(name = "position_speed")
    public String positionSpeed;

    @Column(name = "position_latitude")
    public String positionLatitude;

    @Column(name = "trip_route_id")
    public String tripRouteId;

    @Column(name = "vehicle_label")
    public String vehicleLabel;

    @Column(name = "position_odometer")
    public String positionOdometer;

    @Column(name = "trip_id")
    public String tripId;

    @Column(name = "vehicle_current_status")
    public String vehicleCurrentStatus;

    @Column(name = "geographic_point_lat")
    public String georaphicPointLat;

    @Column(name = "geographic_point_lon")
    public String geographicPointLon;

    @Column(name = "type")
    public String type;

    @Column(name = "coordinates_lat")
    public String coordinatesLat;

    @Column(name = "coordinates_lon")
    public String coordinatesLon;

    @Column(name = "record_timestamp")
    public String recordTimeStamp;

    @Column(name = "date_add")
    public Date dateAdd;

    @Column(name = "alcaldia")
    public String alcaldia;

}
