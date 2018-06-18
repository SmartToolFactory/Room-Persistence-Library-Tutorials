package com.example.tutorial2crudoperations.data.source.local;

import android.support.annotation.NonNull;

import com.example.tutorial2crudoperations.data.User;
import com.example.tutorial2crudoperations.data.source.UserDataSource;

import java.util.List;

public class UserLocalDataSource implements UserDataSource {


    private static volatile UserLocalDataSource INSTANCE;

    private UserDao mUserDao;


    // Prevent direct instantiation.
    private UserLocalDataSource(
            @NonNull UserDao userDao) {
        mUserDao = userDao;
    }

    public static UserLocalDataSource getInstance(
            @NonNull UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (UserLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserLocalDataSource(userDao);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void insertUser(final User user) {
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

        mUserDao.insertUser(user);
    }

    @Override
    public void insertUsers(User... users) {
        mUserDao.insertUsers(users);
    }

    @Override
    public void insertBothUsers(User user1, User user2) {
        mUserDao.insertBothUsers(user1, user2);
    }

    @Override
    public void insertUsersAndFriends(User user, List<User> friends) {
        mUserDao.insertUsersAndFriends(user, friends);
    }

    @Override
    public void updateUsers(User... users) {
        mUserDao.updateUsers(users);
    }

    @Override
    public void deleteUsers(User... users) {
        mUserDao.deleteUsers(users);
    }

    @Override
    public int countUsers() {
        return mUserDao.countUsers();
    }

    @Override
    public User[] loadAllUsers() {
        return mUserDao.loadAllUsers();
    }

    @Override
    public User[] loadAllUsersOlderThan(int minAge) {
        return mUserDao.loadAllUsersOlderThan(minAge);
    }

    @Override
    public User[] loadAllUsersBetweenAges(int minAge, int maxAge) {
        return mUserDao.loadAllUsersBetweenAges(minAge, maxAge);
    }

    @Override
    public List<User> findUserWithName(String search) {
        return mUserDao.findUserWithName(search);
    }

    @Override
    public User getUser(long userId) {
        return mUserDao.getUser(userId);
    }

    @Override
    public List<User> loadAllByIds(int[] userIds) {
        return mUserDao.loadAllByIds(userIds);
    }

    @Override
    public User findByName(String first, String last) {
        return mUserDao.findByName(first, last);
    }

    @Override
    public int deleteAll() {
        return mUserDao.deleteAll();
    }
}
