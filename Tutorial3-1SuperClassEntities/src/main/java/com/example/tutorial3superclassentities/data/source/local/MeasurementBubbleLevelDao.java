package com.example.tutorial3superclassentities.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.tutorial3superclassentities.data.source.model.MeasurementBubbleLevel;

import java.util.List;

@Dao
public interface MeasurementBubbleLevelDao extends BaseDao<MeasurementBubbleLevel> {
    @Query("SELECT * FROM table_clinometer_bubble_level")
    List<MeasurementBubbleLevel> getAll();

    @Query("DELETE  FROM table_clinometer_bubble_level")
    int deleteAll();
}
