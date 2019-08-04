package com.example.tutorial3superclassentities.data.source.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

@Entity(tableName = "table_laser_level_angle")
public class MeasurementLaser extends Measurement {
    @ColumnInfo
    private String bearing;
    @ColumnInfo
    private double angle;
    @ColumnInfo
    private double azimuth;
    @ColumnInfo
    private double pitch;
    @ColumnInfo
    private double roll;

    // Location and Address
    @ColumnInfo
    private double latitude;
    @ColumnInfo
    private double longitude;
    @ColumnInfo
    private double altitude;
    @ColumnInfo
    private String address;
    @Ignore
    private String latitudeString;
    @Ignore
    private String longitudeString;


    public MeasurementLaser() {

    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }


    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitudeString() {
        return latitudeString;
    }

    public void setLatitudeString(String latitudeString) {
        this.latitudeString = latitudeString;
    }

    public String getLongitudeString() {
        return longitudeString;
    }

    public void setLongitudeString(String longitudeString) {
        this.longitudeString = longitudeString;
    }
}
