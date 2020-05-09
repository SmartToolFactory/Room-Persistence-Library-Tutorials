package com.example.tutorial4migratingdatabase.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// By default Room creates a table with class name
@Entity(tableName = "users")
public class User {


    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "user_name")
    private String name;

    @ColumnInfo(name = "user_email")
    private String email;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toString() {
        return "uid: " + uid
                + ", name: " + name
                + ", email: " + email;
    }
}
