package com.softcrud.TestPipeLine.Pojos.Metrobus;

import java.util.List;

public class Fields {
    public String vehicle_id;
    public String trip_start_date;
    public String date_updated;
    public String position_longitude;
    public String trip_schedule_relationship;
    public String position_speed;
    public String position_latitude;
    public String trip_route_id;
    public String vehicle_label;
    public String position_odometer;
    public String trip_id;
    public String vehicle_current_status;
    public List<String> geographic_point;

    public Fields(String vehicle_id, String trip_start_date, String date_updated, String position_longitude, String trip_schedule_relationship, String position_speed, String position_latitude, String trip_route_id, String vehicle_label, String position_odometer, String trip_id, String vehicle_current_status, List<String> geographic_point) {
        this.vehicle_id = vehicle_id;
        this.trip_start_date = trip_start_date;
        this.date_updated = date_updated;
        this.position_longitude = position_longitude;
        this.trip_schedule_relationship = trip_schedule_relationship;
        this.position_speed = position_speed;
        this.position_latitude = position_latitude;
        this.trip_route_id = trip_route_id;
        this.vehicle_label = vehicle_label;
        this.position_odometer = position_odometer;
        this.trip_id = trip_id;
        this.vehicle_current_status = vehicle_current_status;
        this.geographic_point = geographic_point;
    }
}
