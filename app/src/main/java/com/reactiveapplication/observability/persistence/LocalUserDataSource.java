package com.reactiveapplication.observability.persistence;

import com.reactiveapplication.observability.UserDataSource;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalUserDataSource implements UserDataSource {

    private final UserDao mUserDao;

    public LocalUserDataSource(UserDao userDao) {
        mUserDao= userDao;
    }

    @Override
    public Flowable<User> getUser() {
        return mUserDao.getUser();
    }

    @Override
    public Completable insertOrUpdateUser(User user) {
        return mUserDao.insertUser(user);
    }

    @Override
    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }
}
