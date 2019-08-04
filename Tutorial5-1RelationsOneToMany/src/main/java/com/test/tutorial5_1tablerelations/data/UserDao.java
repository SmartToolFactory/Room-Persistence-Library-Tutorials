package com.test.tutorial5_1tablerelations.data;

import android.arch.lifecycle.LiveData;
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

    @Insert
    public abstract void insertPets(List<Pet> pets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertPet(Pet pet);

    // Transaction executes operations as a Single Atomic Operation
    @Transaction
    public void insertUserAndPets(User user, List<Pet> pets) {

        long id = insertUser(user);
        for (Pet pet : pets) {
            pet.userId = id;
        }

        insertPets(pets);
    }


//    @Transaction
//    @Query("SELECT * FROM users")
//    public abstract List<UserAndAllPets> getUserAndAllPets();

    @Transaction
    @Query("SELECT * FROM users")
    public abstract LiveData<List<UserAndAllPets>> getUserAndAllPets();


    /**
     * JOIN query that returns pets owned by an owner with specified id
     * @param id of the user that owns pet
     * @return pets owned by this user
     */
    @Query("SELECT * FROM pets INNER JOIN users ON users.id = pets.userId WHERE users.id LIKE :id ")
    public abstract List<Pet> getPetsOwnedByUser(long id);


    @Delete
    abstract void delete(User user);


}
