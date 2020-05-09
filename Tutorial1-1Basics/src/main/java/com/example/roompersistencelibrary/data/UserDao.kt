package com.example.roompersistencelibrary.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * This interface is used for CRUD operation on database
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User?): Long

    @get:Query("SELECT * FROM users")
    val all: List<User?>?

    @Delete
    fun delete(user: User?)
}