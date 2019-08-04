package com.example.tutorial2crudoperations.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tutorial2crudoperations.data.User;

import static com.example.tutorial2crudoperations.data.source.local.UserDatabase.DATABASE_VERSION;

@Database(entities = {User.class}, version = DATABASE_VERSION)
public abstract class UserDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 1;

    private static UserDatabase INSTANCE;

    private static final Object sLock = new Object();

    public abstract UserDao userDao();

    public static UserDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}
