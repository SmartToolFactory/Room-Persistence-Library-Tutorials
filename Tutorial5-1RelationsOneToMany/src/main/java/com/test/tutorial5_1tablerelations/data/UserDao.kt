package com.test.tutorial5_1tablerelations.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


/**
 * This interface is used for CRUD operation on database
 */
@Dao
abstract class UserDao {


    @Insert
    abstract fun insertUser(user: User): Long?

    @Insert
    abstract fun insertPets(pets: List<Pet>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPet(pet: Pet): Long


    /**
     * This method uses [Embedded] and [Relation] annotations to create a class that cotains [User] and list of [Pet]s
     */
    @Transaction
    @Query("SELECT * FROM users")
    abstract fun getUserAndAllPets(): LiveData<List<UserAndAllPets>>



    // Transaction executes operations as a Single Atomic Operation
    // 🔥 This method should be open to work with @Transaction
    @Transaction
    open fun insertUserAndPets(user: User, pets: List<Pet>) {

        val id = insertUser(user)!!
        for (pet in pets) {
            pet.userId = id
        }

        insertPets(pets)
    }


    /**
     * JOIN query that returns pets owned by an owner with specified petId
     * @param id of the user that owns pet
     * @return pets owned by this user
     */
    @Query("SELECT * FROM pets INNER JOIN users ON users.id = pets.userId WHERE users.id LIKE :id ")
    abstract fun getPetsOwnedByUser(id: Long): List<Pet>


    @Delete
    internal abstract fun delete(user: User)


}
