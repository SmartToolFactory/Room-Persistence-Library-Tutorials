package com.example.tutorial2crudoperations.data.source

import com.example.tutorial2crudoperations.data.User

interface UserDataSource {
    /*
     * INSERT
     *
     * insert can return (long, long[] or List<Long>) id or ids of rows inserted
     */
    fun insertUser(user: User)
    fun insertUsers(vararg users: User)
    fun insertBothUsers(user1: User, user2: User)

    fun insertUsersAndFriends(user: User, friends: List<User>)

    /*
     * UPDATE
     *
     * update can return (int) number of rows updated
     */
    fun updateUsers(vararg users: User)

    /*
     * DELETE
     * delete can return (int) number of deleted rows
     */
    fun deleteUsers(vararg users: User)

    /*
     * QUERY
     */
    // Get number of rows
    fun countUsers(): Int

    // Get all users
    fun loadAllUsers(): Array<User>

    // Get users above minAge
    fun loadAllUsersOlderThan(minAge: Int): Array<User>

    // Get users >minAge and <maxAge
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

    // Get users start with search keyword in name or last name
    fun findUserWithName(search: String): List<User>

    // Get user with given id
    fun getUser(userId: Long): User

    // Get users with user ids
    fun loadAllByIds(userIds: Array<Int>): List<User>

    // Get user that contain first and last name
    fun findByName(first: String, last: String): User

    // Delete every row in user table
    fun deleteAll(): Int
}