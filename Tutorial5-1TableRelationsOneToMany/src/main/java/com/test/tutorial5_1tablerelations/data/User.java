package com.test.tutorial5_1tablerelations.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// By default Room creates a table with class name
@Entity(tableName = "users")
public class User  {


    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "user_email")
    public String email;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }
}
