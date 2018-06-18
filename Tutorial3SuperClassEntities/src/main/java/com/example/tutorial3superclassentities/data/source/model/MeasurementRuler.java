package com.example.tutorial3superclassentities.data.source.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "table_ruler")
public class MeasurementRuler extends Measurement {
    @ColumnInfo
    private float length;
    @ColumnInfo
    private float width;
    @ColumnInfo
    private float area;

    public MeasurementRuler() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the area
     */
    public float getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(float area) {
        this.area = area;
    }


}
