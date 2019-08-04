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
public interface UserDao {

    @Insert
    public long insertUser(User user);

    @Query("SELECT * FROM users")
    public List<User> getAll();

    @Delete
    public void delete(User user);

    @Query("DELETE FROM users")
    public void deleteAll();

}
