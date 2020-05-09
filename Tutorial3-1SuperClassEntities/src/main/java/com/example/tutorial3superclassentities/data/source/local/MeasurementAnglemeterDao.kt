package com.example.tutorial3superclassentities.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.example.tutorial3superclassentities.data.source.model.MeasurementAnglemeter

@Dao
interface MeasurementAnglemeterDao : BaseDao<MeasurementAnglemeter> {

    @Query("SELECT * FROM table_clinometer_angle")
    fun getAll(): List<MeasurementAnglemeter>

    @Query("DELETE  FROM table_clinometer_angle")
    fun deleteAll(): Int
}