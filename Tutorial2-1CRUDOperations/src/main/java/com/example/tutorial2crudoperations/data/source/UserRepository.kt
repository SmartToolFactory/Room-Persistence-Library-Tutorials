package com.example.tutorial2crudoperations.data.source

import com.example.tutorial2crudoperations.data.User

// TODO Do User Data Caching inside this class
class UserRepository private constructor(private val mLocalUserDataSource: UserDataSource) :
    UserDataSource {
    override fun insertUser(user: User) {
        mLocalUserDataSource.insertUser(user)
    }

    override fun insertUsers(vararg users: User) {
        mLocalUserDataSource.insertUsers(*users)
    }

    override fun insertBothUsers(
        user1: User,
        user2: User
    ) {
        mLocalUserDataSource.insertUsers(user1, user2)
    }

    override fun insertUsersAndFriends(
        user: User,
        friends: List<User>
    ) {
        mLocalUserDataSource.insertUsersAndFriends(user, friends)
    }

    override fun updateUsers(vararg users: User) {
        mLocalUserDataSource.insertUsers(*users)
    }

    override fun deleteUsers(vararg users: User) {
        mLocalUserDataSource.insertUsers(*users)
    }

    override fun countUsers(): Int {
        return mLocalUserDataSource.countUsers()
    }

    override fun loadAllUsers(): Array<User> {
        return mLocalUserDataSource.loadAllUsers()
    }

    override fun loadAllUsersOlderThan(minAge: Int): Array<User> {
        return mLocalUserDataSource.loadAllUsersOlderThan(minAge)
    }

    override fun loadAllUsersBetweenAges(
        minAge: Int,
        maxAge: Int
    ): Array<User> {
        return mLocalUserDataSource.loadAllUsersBetweenAges(minAge, maxAge)
    }

    override fun findUserWithName(search: String): List<User> {
        return mLocalUserDataSource.findUserWithName(search)
    }

    override fun getUser(userId: Long): User {
        return mLocalUserDataSource.getUser(userId)
    }

    override fun loadAllByIds(userIds: Array<Int>): List<User> {
        return mLocalUserDataSource.loadAllByIds(userIds)
    }

    override fun findByName(
        first: String,
        last: String
    ): User {
        return mLocalUserDataSource.findByName(first, last)
    }

    override fun deleteAll(): Int {
        return mLocalUserDataSource.deleteAll()
    }

    companion object {
        private lateinit var INSTANCE: UserRepository

        @JvmStatic
        fun getInstance(userDataSource: UserDataSource): UserRepository {

            if (INSTANCE == null) {
                INSTANCE = UserRepository(userDataSource)
            }

            return INSTANCE
        }
    }

}