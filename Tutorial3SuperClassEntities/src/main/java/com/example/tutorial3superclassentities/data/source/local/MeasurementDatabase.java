package com.example.tutorial3superclassentities.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tutorial3superclassentities.data.source.model.MeasurementAnglemeter;
import com.example.tutorial3superclassentities.data.source.model.MeasurementBubbleLevel;
import com.example.tutorial3superclassentities.data.source.model.MeasurementLaser;
import com.example.tutorial3superclassentities.data.source.model.MeasurementProtractor;
import com.example.tutorial3superclassentities.data.source.model.MeasurementRuler;

import static com.example.tutorial3superclassentities.data.source.local.MeasurementDatabase.DATABASE_VERSION;

@Database(entities = {MeasurementAnglemeter.class,
        MeasurementBubbleLevel.class,
        MeasurementLaser.class,
        MeasurementProtractor.class,
        MeasurementRuler.class}
        , version = DATABASE_VERSION)
public abstract class MeasurementDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "clinometer.db";
    public static final int DATABASE_VERSION = 1;

    private static MeasurementDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract MeasurementAnglemeterDao measurementAnglemeterDao();

    public abstract MeasurementBubbleLevelDao measurementBubbleLevelDao();

    public static MeasurementDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MeasurementDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}
