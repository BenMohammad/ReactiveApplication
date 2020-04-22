package com.reactiveapplication.observability;

import android.content.Context;

import com.reactiveapplication.observability.persistence.LocalUserDataSource;
import com.reactiveapplication.observability.persistence.UsersDatabase;
import com.reactiveapplication.observability.ui.ViewModelFactory;

public class Injection {

    public static UserDataSource provideUserDataSource(Context context) {
        UsersDatabase database = UsersDatabase.getInstance(context);
        return new LocalUserDataSource(database.userDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
