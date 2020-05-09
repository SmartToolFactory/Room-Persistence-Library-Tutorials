package com.example.tutorial4migratingdatabase.db;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
