package com.example.tutorial3superclassentities.data.source.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "table_clinometer_angle")
public class MeasurementAnglemeter extends Measurement {

    @ColumnInfo(name = "angle")
    private double angleSingle = 0;

    public double getAngleSingle() {
        return angleSingle;
    }

    public void setAngleSingle(double angleSingle) {
        this.angleSingle = angleSingle;
    }

}
