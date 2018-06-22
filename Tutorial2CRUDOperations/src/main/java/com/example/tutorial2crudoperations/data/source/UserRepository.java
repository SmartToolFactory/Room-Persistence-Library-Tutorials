package com.example.tutorial2crudoperations.data.source;

import com.example.tutorial2crudoperations.data.User;

import java.util.List;

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private UserDataSource mLocalUserDataSource;

    private UserRepository(UserDataSource userDataSource) {
        mLocalUserDataSource = userDataSource;
    }

    public static UserRepository getInstance(UserDataSource userDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(userDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void insertUser(User user) {
        mLocalUserDataSource.insertUser(user);
    }

    @Override
    public void insertUsers(User... users) {
        mLocalUserDataSource.insertUsers(users);

    }

    @Override
    public void insertBothUsers(User user1, User user2) {
        mLocalUserDataSource.insertUsers(user1, user2);
    }

    @Override
    public void insertUsersAndFriends(User user, List<User> friends) {
        mLocalUserDataSource.insertUsersAndFriends(user, friends);
    }

    @Override
    public void updateUsers(User... users) {
        mLocalUserDataSource.insertUsers(users);
    }

    @Override
    public void deleteUsers(User... users) {
        mLocalUserDataSource.insertUsers(users);
    }

    @Override
    public int countUsers() {
        return mLocalUserDataSource.countUsers();
    }

    @Override
    public User[] loadAllUsers() {
        return mLocalUserDataSource.loadAllUsers();
    }

    @Override
    public User[] loadAllUsersOlderThan(int minAge) {
        return mLocalUserDataSource.loadAllUsersOlderThan(minAge);
    }

    @Override
    public User[] loadAllUsersBetweenAges(int minAge, int maxAge) {
        return mLocalUserDataSource.loadAllUsersBetweenAges(minAge, maxAge);
    }

    @Override
    public List<User> findUserWithName(String search) {
        return mLocalUserDataSource.findUserWithName(search);
    }

    @Override
    public User getUser(long userId) {
        return mLocalUserDataSource.getUser(userId);
    }

    @Override
    public List<User> loadAllByIds(int[] userIds) {
        return mLocalUserDataSource.loadAllByIds(userIds);
    }

    @Override
    public User findByName(String first, String last) {
        return mLocalUserDataSource.findByName(first, last);
    }

    @Override
    public int deleteAll() {
        return mLocalUserDataSource.deleteAll();
    }
}
