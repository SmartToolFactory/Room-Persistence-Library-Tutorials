package com.test.tutorial5_1tablerelations.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

/**
 * This interface is used for CRUD operation on database
 */
@Dao
public abstract class UserDao {

    @Insert
    public abstract Long insertUser(User user);

    public abstract void insertPets(List<Pet> pets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertPet(Pet pet);

    @Insert
    public abstract void insertAllPets(List<Pet> pets);

    // Transaction executes operations as a Single Atomic Operation
    @Transaction
    public void insertUserAndPets(User user, List<Pet> pets) {

        long id = insertUser(user);
        for (Pet pet : pets) {
            pet.userId = id;
        }

        insertPets(pets);
    }

    @Query("SELECT * FROM users")
    public abstract List<User> getAll();


    @Transaction
    @Query("SELECT * FROM users")
    public abstract List<UserAndAllPets> getUserAndAllPets();

    @Delete
    abstract void delete(User user);


}
