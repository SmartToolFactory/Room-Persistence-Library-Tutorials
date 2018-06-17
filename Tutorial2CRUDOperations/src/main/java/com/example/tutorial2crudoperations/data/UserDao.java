package com.example.tutorial2crudoperations.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
    public void insertUser(User user);

    @Insert
    public void insertUsers(User... users);

    @Insert
    public void insertBothUsers(User user1, User user2);

    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);

    /*
     * UPDATE
     *
     * update can return (int) number of rows updated
     */
    @Update
    public void updateUsers(User... users);


    /*
     * DELETE
     * delete can return (int) number of deleted rows
     */
    @Delete
    public void deleteUsers(User... users);

    /*
     * QUERY
     */
    // Get number of rows
    @Query("SELECT COUNT(*) from user")
    int countUsers();

    // Get all users
    @Query("SELECT * FROM user")
    public User[] loadAllUsers();

    // Get users above minAge
    @Query("SELECT * FROM user WHERE age > :minAge")
    public User[] loadAllUsersOlderThan(int minAge);

    // Get users >minAge and <maxAge
    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    public User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    // Get users start with search keyword in name or last name
    @Query("SELECT * FROM user WHERE first_name LIKE :search "
            + "OR last_name LIKE :search")
    public List<User> findUserWithName(String search);

    // Get user with given id
    @Query("SELECT * FROM user WHERE id = :userId")
    public User getUser(long userId);

    // Get users with user ids
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    public List<User> loadAllByIds(int[] userIds);

    // Get user that contain first and last name
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    public User findByName(String first, String last);
}