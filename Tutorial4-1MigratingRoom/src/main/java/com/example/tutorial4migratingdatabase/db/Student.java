package com.example.tutorial4migratingdatabase.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// By default Room creates a table with class name
@Entity(tableName = "students")
public class Student {


    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "student_name")
    private String name;

    @ColumnInfo(name = "student_number")
    private int studentNumber;


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

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String toString() {
        return "uid: " + uid
                + ", name: " + name
                + ", studentNumber: " + studentNumber;
    }

}
