package com.example.tutorial3superclassentities.data.source.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "table_clinometer_bubble_level")
public class MeasurementBubbleLevel extends Measurement {

    @ColumnInfo(name = "angle_horizontal")
    private double angleHorizontal = 0;
    @ColumnInfo(name = "angle_vertical")
    private double angleVertical = 0;

    public double getAngleHorizontal() {
        return angleHorizontal;
    }

    public void setAngleHorizontal(double angleHorizontal) {
        this.angleHorizontal = angleHorizontal;
    }

    public double getAngleVertical() {
        return angleVertical;
    }

    public void setAngleVertical(double angleVertical) {
        this.angleVertical = angleVertical;
    }

}
