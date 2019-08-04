package com.example.tutorial4migratingdatabase.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This interface is used for CRUD operation on database
 */
@Dao
public interface StudentDao {

    @Insert
    public long insertStudent(Student student);

    @Query("SELECT * FROM students")
    public List<Student> getAll();

    @Delete
    public void delete(Student student);

    @Query("DELETE FROM students")
    public void deleteAll();

}
