package com.example.tutorial2crudoperations.data.source.local;


import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.tutorial2crudoperations.data.User


@Dao
interface UserDao {
    /*
     * INSERT
     *
     * insert can return (long, long[] or List<Long>) id or ids of rows inserted
     */
    @Insert(onConflict = REPLACE)
    fun insertUser(user: User): Long

    @Insert
    fun insertUsers(vararg users: User): List<Long>

    @Insert
    fun insertBothUsers(user1: User, user2: User): Array<Long>

    @Insert
    fun insertUsersAndFriends(user: User, friends: List<User>): List<Long>

    /*
     * UPDATE
     *
     * update can return (int) number of rows updated
     */
    @Update
    fun updateUsers(vararg users: User)

    /*
     * DELETE
     * delete can return (int) number of deleted rows
     */
    @Delete
    fun deleteUsers(vararg users: User)

    /*
     * QUERY
     */
    // Get number of rows
    @Query("SELECT COUNT(*) from user")
    fun countUsers(): Int

    // Get all users
    @Query("SELECT * FROM user")
    fun loadAllUsers(): Array<User>

    // Get users above minAge
    @Query("SELECT * FROM user WHERE age > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): Array<User>

    // Get users >minAge and <maxAge
    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

    // Get users start with search keyword in name or last name
    @Query("SELECT * FROM user WHERE first_name LIKE :search OR last_name LIKE :search")
    fun findUserWithName(search: String): List<User>

    // Get user with given id
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUser(userId: Long): User

    // Get users with user ids
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: Array<Int>): List<User>

    // Get user that contain first and last name
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    // Delete every row in user table
    @Query("DELETE FROM user")
    fun deleteAll(): Int
}