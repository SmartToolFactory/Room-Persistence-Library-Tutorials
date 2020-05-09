package com.example.tutorial3superclassentities.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "table_ruler")
class MeasurementRuler : Measurement() {

    @ColumnInfo
    var length = 0f

    @ColumnInfo
    var width = 0f

}