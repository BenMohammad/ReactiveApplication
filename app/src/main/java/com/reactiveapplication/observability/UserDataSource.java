package com.reactiveapplication.observability;

import com.reactiveapplication.observability.persistence.User;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserDataSource {

    Flowable<User> getUser();

    Completable insertOrUpdateUser(User user);

    void deleteAllUsers();
}
