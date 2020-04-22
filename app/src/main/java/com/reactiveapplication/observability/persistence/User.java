package com.reactiveapplication.observability.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "users")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private String mId;

    @ColumnInfo(name = "username")
    private String mUserName;

    public User() {}

    @Ignore
    public User(String userName) {
        mId = UUID.randomUUID().toString();
        this.mUserName = userName;
    }

    public User(@NonNull String id, String userName) {
        this.mId = id;
        this.mUserName = userName;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }
}
