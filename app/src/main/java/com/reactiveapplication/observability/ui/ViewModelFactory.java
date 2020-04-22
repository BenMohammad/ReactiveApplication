package com.reactiveapplication.observability.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.reactiveapplication.observability.UserDataSource;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final UserDataSource mDataSource;

    public ViewModelFactory(UserDataSource userDataSource) {
        mDataSource = userDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
