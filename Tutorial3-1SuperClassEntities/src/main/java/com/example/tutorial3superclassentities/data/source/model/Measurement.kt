package com.example.tutorial3superclassentities.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity
abstract class Measurement {
    /**
     * @return the id
     */
    /**
     * @param id the id to set
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    /**
     * @return the title
     */
    /**
     * @param title the title to set
     */
    @ColumnInfo(name = "title")
    var title: String? = null

    /**
     * @return the note
     */
    /**
     * @param note the note to set
     */
    @ColumnInfo(name = "note")
    var note: String? = null

    /**
     * @return the unit
     */
    /**
     * @param unit the unit to set
     */
    @ColumnInfo(name = "unit")
    var unit: String? = null

    /**
     * @return the date
     */
    /**
     * @param date the date to set
     */
    @ColumnInfo(name = "date")
    var date: Long = System.currentTimeMillis()

    val formattedDate: String
        get() = DateFormat.getInstance().format(date)
}