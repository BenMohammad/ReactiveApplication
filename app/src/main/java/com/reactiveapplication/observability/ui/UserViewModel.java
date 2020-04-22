package com.reactiveapplication.observability.ui;

import androidx.lifecycle.ViewModel;

import com.reactiveapplication.observability.UserDataSource;
import com.reactiveapplication.observability.persistence.User;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserViewModel extends ViewModel {

    private final UserDataSource mDataSource;
    private User mUser;

    public UserViewModel(UserDataSource userDataSource) {
        mDataSource = userDataSource;
    }

    public Flowable<String> getUserName() {
        return mDataSource.getUser()
                .map(user -> {
                    mUser= user;
                    return user.getUserName();
                }
                );
    }



    public Completable updateUserName(final String userName) {
        mUser = mUser == null ? new User(userName) : new User(mUser.getId(), userName);
        return mDataSource.insertOrUpdateUser(mUser);

    }}
