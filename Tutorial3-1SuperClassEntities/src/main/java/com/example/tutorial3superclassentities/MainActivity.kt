package com.example.tutorial3superclassentities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.tutorial3superclassentities.data.source.local.MeasurementDatabase
import com.example.tutorial3superclassentities.data.source.model.MeasurementAnglemeter
import com.example.tutorial3superclassentities.data.source.model.MeasurementBubbleLevel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_measurement)

        val measurementDatabase =
            Room.databaseBuilder(
                applicationContext,
                MeasurementDatabase::class.java,
                MeasurementDatabase.DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()

        val measurementAnglemeterDao =
            measurementDatabase.measurementAnglemeterDao()

        val measurementBubbleLevelDao =
            measurementDatabase.measurementBubbleLevelDao()

        // Add Dummy Measurements
        val measurementAnglemeter = MeasurementAnglemeter()
        measurementAnglemeter.angleSingle = 12.0
        measurementAnglemeter.title = "Title1"
        measurementAnglemeter.note = "Note1"
        measurementAnglemeter.date = System.currentTimeMillis()
        measurementAnglemeterDao!!.insert(measurementAnglemeter)

        measurementAnglemeter.angleSingle = 22.0
        measurementAnglemeter.title = "Title2"
        measurementAnglemeter.note = "Note2"
        measurementAnglemeter.date = System.currentTimeMillis()
        measurementAnglemeterDao.insert(measurementAnglemeter)
        measurementAnglemeter.angleSingle = 32.0
        measurementAnglemeter.title = "Title3"
        measurementAnglemeter.note = "Note3"
        measurementAnglemeter.date = System.currentTimeMillis()
        measurementAnglemeterDao.insert(measurementAnglemeter)

        val measurementBubbleLevel = MeasurementBubbleLevel()
        measurementBubbleLevel.title = "Bubble Example"
        measurementBubbleLevel.note = "Bubble note"
        measurementBubbleLevel.angleHorizontal = 4.0
        measurementBubbleLevel.angleVertical = 30.0
        measurementBubbleLevel.date = System.currentTimeMillis()
        measurementBubbleLevelDao!!.insert(measurementBubbleLevel)
        val measurementListAnglemeter: List<MeasurementBubbleLevel> =
            measurementBubbleLevelDao.getAll()
        val stringBuilder = StringBuilder()

        if (measurementListAnglemeter != null && measurementListAnglemeter.size > 0) {

            for (measurement in measurementListAnglemeter) {
                stringBuilder.append("Title: ${measurement.title}, angle: ${measurement.angleHorizontal}, note: ${measurement.note}, date: ${measurement.formattedDate}\n")
            }
            textView.text = stringBuilder.toString()
        }
    }
}