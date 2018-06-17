package com.example.roompersistencelibrary.source;

import java.util.List;

public interface UserDataSource {
    /*
     * INSERT
     *
     * insert can return (long, long[] or List<Long>) id or ids of rows inserted
     */
    void insertUser(User user);

    void insertUsers(User... users);

    void insertBothUsers(User user1, User user2);

    void insertUsersAndFriends(User user, List<User> friends);

    /*
     * UPDATE
     *
     * update can return (int) number of rows updated
     */
    void updateUsers(User... users);


    /*
     * DELETE
     * delete can return (int) number of deleted rows
     */
    void deleteUsers(User... users);

    /*
     * QUERY
     */
    // Get number of rows
    int countUsers();

    // Get all users
    User[] loadAllUsers();

    // Get users above minAge
    User[] loadAllUsersOlderThan(int minAge);

    // Get users >minAge and <maxAge
    User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    // Get users start with search keyword in name or last name
    List<User> findUserWithName(String search);

    // Get user with given id
    User getUser(long userId);

    // Get users with user ids
    List<User> loadAllByIds(int[] userIds);

    // Get user that contain first and last name
    User findByName(String first, String last);
}
