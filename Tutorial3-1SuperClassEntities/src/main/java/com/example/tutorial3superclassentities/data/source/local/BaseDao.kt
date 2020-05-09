package com.example.tutorial3superclassentities.data.source.local

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long

    @Update
    fun update(entity: T): Int

    @Delete
    fun delete(entity: T): Int
}