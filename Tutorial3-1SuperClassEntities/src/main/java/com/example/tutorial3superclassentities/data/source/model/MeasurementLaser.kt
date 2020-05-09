package com.example.tutorial3superclassentities.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "table_laser_level_angle")
class MeasurementLaser : Measurement() {

    @ColumnInfo
    var bearing: String? = null

    @ColumnInfo
    var angle = 0.0

    @ColumnInfo
    var azimuth = 0.0

    @ColumnInfo
    var pitch = 0.0

    @ColumnInfo
    var roll = 0.0

    // Location and Address
    @ColumnInfo
    var latitude = 0.0

    @ColumnInfo
    var longitude = 0.0

    @ColumnInfo
    var altitude = 0.0

    @ColumnInfo
    var address: String? = null

    @Ignore
    var latitudeString: String? = null

    @Ignore
    var longitudeString: String? = null

}