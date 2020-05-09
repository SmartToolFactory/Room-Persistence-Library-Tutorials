package com.example.tutorial3superclassentities.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_clinometer_bubble_level")
class MeasurementBubbleLevel : Measurement() {

    @ColumnInfo(name = "angle_horizontal")
    var angleHorizontal = 0.0

    @ColumnInfo(name = "angle_vertical")
    var angleVertical = 0.0

}