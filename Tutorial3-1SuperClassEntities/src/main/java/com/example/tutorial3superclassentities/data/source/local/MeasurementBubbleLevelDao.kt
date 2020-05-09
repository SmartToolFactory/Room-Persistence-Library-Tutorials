package com.example.tutorial3superclassentities.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.example.tutorial3superclassentities.data.source.model.MeasurementBubbleLevel

@Dao
interface MeasurementBubbleLevelDao : BaseDao<MeasurementBubbleLevel> {

    @Query("SELECT * FROM table_clinometer_bubble_level")
    fun getAll(): List<MeasurementBubbleLevel>

    @Query("DELETE  FROM table_clinometer_bubble_level")
    fun deleteAll(): Int
}