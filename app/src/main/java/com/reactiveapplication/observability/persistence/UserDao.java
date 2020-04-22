package com.reactiveapplication.observability.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM Users LIMIT 1 ")
    Flowable<User> getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    @Query("DELETE FROM Users")
    void deleteAllUsers();
}
