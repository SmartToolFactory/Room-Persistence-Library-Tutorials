package com.example.tutorial3superclassentities.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_clinometer_angle")
data class MeasurementAnglemeter(
    @ColumnInfo(name = "angle")
    var angleSingle: Double = 0.0

) : Measurement()