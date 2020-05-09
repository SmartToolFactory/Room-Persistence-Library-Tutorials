package com.example.tutorial3superclassentities.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tutorial3superclassentities.data.source.local.MeasurementDatabase
import com.example.tutorial3superclassentities.data.source.model.*

@Database(
    entities = [MeasurementAnglemeter::class,
        MeasurementBubbleLevel::class,
        MeasurementLaser::class,
        MeasurementProtractor::class,
        MeasurementRuler::class],
    version = MeasurementDatabase.DATABASE_VERSION
)
abstract class MeasurementDatabase : RoomDatabase() {

    abstract fun measurementAnglemeterDao(): MeasurementAnglemeterDao?
    abstract fun measurementBubbleLevelDao(): MeasurementBubbleLevelDao?

    companion object {

        const val DATABASE_NAME = "database_sample.db"
        const val DATABASE_VERSION = 1

        private var INSTANCE: MeasurementDatabase? = null
        private val sLock = Any()

        fun getInstance(context: Context): MeasurementDatabase? {

            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            MeasurementDatabase::class.java,
                            DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE
            }
        }
    }
}