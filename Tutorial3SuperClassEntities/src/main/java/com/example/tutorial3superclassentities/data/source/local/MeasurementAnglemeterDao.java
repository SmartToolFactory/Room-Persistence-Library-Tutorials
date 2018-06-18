package com.example.tutorial3superclassentities.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.tutorial3superclassentities.data.source.model.MeasurementAnglemeter;

import java.util.List;

@Dao
public interface MeasurementAnglemeterDao extends BaseDao<MeasurementAnglemeter> {
    @Query("SELECT * FROM table_clinometer_angle")
   List<MeasurementAnglemeter> getAll();

    @Query("DELETE  FROM table_clinometer_angle")
   int deleteAll();

}
