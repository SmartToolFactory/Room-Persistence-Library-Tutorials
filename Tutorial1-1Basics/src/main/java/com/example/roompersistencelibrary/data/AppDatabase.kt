package com.example.roompersistencelibrary.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Entities are tables in database
 */
@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?
}