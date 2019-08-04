package com.test.tutorial5_1tablerelations.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

// By default Room creates a table with class name
@Entity(tableName = "users")
class User {


    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "user_name")
    var name: String? = null

    @ColumnInfo(name = "user_email")
    var email: String? = null
}
