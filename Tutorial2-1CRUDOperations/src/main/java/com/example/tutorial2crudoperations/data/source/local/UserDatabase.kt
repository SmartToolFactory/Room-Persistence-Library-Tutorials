package com.example.tutorial2crudoperations.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tutorial2crudoperations.data.User
import com.example.tutorial2crudoperations.data.source.local.UserDatabase

@Database(
    entities = [User::class],
    version = UserDatabase.DATABASE_VERSION
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "user.db"
        const val DATABASE_VERSION = 1
        private lateinit  var INSTANCE: UserDatabase
        private val sLock = Any()

        fun getInstance(context: Context): UserDatabase {

            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE
            }
        }
    }
}