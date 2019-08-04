package com.test.tutorial5_1tablerelations.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Entities are tables in database
 */
@Database(entities = [User::class, Pet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

