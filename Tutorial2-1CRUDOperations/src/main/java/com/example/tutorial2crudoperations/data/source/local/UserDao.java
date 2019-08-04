package com.example.tutorial2crudoperations.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tutorial2crudoperations.data.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    /*
     * INSERT
     *
     * insert can return (long, long[] or List<Long>) id or ids of rows inserted
     */
    @Insert(onConflict = REPLACE)
    long insertUser(User user);

    @Insert
    List<Long> insertUsers(User... users);

    @Insert
    long[] insertBothUsers(User user1, User user2);

    @Insert
    List<Long> insertUsersAndFriends(User user, List<User> friends);

    /*
     * UPDATE
     *
     * update can return (int) number of rows updated
     */
    @Update
    void updateUsers(User... users);


    /*
     * DELETE
     * delete can return (int) number of deleted rows
     */
    @Delete
    void deleteUsers(User... users);

    /*
     * QUERY
     */
    // Get number of rows
    @Query("SELECT COUNT(*) from user")
    int countUsers();

    // Get all users
    @Query("SELECT * FROM user")
    User[] loadAllUsers();

    // Get users above minAge
    @Query("SELECT * FROM user WHERE age > :minAge")
    User[] loadAllUsersOlderThan(int minAge);

    // Get users >minAge and <maxAge
    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    // Get users start with search keyword in name or last name
    @Query("SELECT * FROM user WHERE first_name LIKE :search "
            + "OR last_name LIKE :search")
    List<User> findUserWithName(String search);

    // Get user with given id
    @Query("SELECT * FROM user WHERE id = :userId")
    User getUser(long userId);

    // Get users with user ids
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    // Get user that contain first and last name
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    // Delete every row in user table
    @Query("DELETE FROM user")
    int deleteAll();
}