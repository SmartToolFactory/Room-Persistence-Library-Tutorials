package com.example.roompersistencelibrary.data

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// By default Room creates a table with class name
@Entity(tableName = "users")
class User : BaseObservable() {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "user_name")
    var name: String? = null

    @ColumnInfo(name = "user_email")
    var email: String? = null

}