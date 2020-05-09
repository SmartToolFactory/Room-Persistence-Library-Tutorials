package com.example.tutorial2crudoperations.data.source.local

import com.example.tutorial2crudoperations.data.User
import com.example.tutorial2crudoperations.data.source.UserDataSource

// TODO ADD THREADING OR CHANGE List<User> to LiveData<List<User>>
class UserLocalDataSource // Prevent direct instantiation.
private constructor(
    private val mUserDao: UserDao
) : UserDataSource {
    override fun insertUser(user: User) {
        /*
            Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUser(user);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
         */
        mUserDao.insertUser(user)
    }

    override fun insertUsers(vararg users: User) {
        mUserDao.insertUsers(*users)
    }

    override fun insertBothUsers(
        user1: User,
        user2: User
    ) {
        mUserDao.insertBothUsers(user1, user2)
    }

    override fun insertUsersAndFriends(
        user: User,
        friends: List<User>
    ) {
        mUserDao.insertUsersAndFriends(user, friends)
    }

    override fun updateUsers(vararg users: User) {
        mUserDao.updateUsers(*users)
    }

    override fun deleteUsers(vararg users: User) {
        mUserDao.deleteUsers(*users)
    }

    override fun countUsers(): Int {
        return mUserDao.countUsers()
    }

    override fun loadAllUsers(): Array<User> {
        return mUserDao.loadAllUsers()
    }

    override fun loadAllUsersOlderThan(minAge: Int): Array<User> {
        return mUserDao.loadAllUsersOlderThan(minAge)
    }

    override fun loadAllUsersBetweenAges(
        minAge: Int,
        maxAge: Int
    ): Array<User> {
        return mUserDao.loadAllUsersBetweenAges(minAge, maxAge)
    }

    override fun findUserWithName(search: String): List<User> {
        return mUserDao.findUserWithName(search)
    }

    override fun getUser(userId: Long): User {
        return mUserDao.getUser(userId)
    }

    override fun loadAllByIds(userIds: Array<Int>): List<User> {
        return mUserDao.loadAllByIds(userIds)
    }

    override fun findByName(
        first: String,
        last: String
    ): User {
        return mUserDao.findByName(first, last)
    }

    override fun deleteAll(): Int {
        return mUserDao.deleteAll()
    }

    companion object {
        @Volatile
        private lateinit var INSTANCE: UserLocalDataSource

        @JvmStatic
        fun getInstance(
            userDao: UserDao
        ): UserLocalDataSource {

            if (INSTANCE == null) {
                synchronized(UserLocalDataSource::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = UserLocalDataSource(userDao)
                    }
                }
            }
            return INSTANCE
        }
    }

}