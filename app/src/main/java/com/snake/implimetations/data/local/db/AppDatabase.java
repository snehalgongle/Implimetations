package com.snake.implimetations.data.local.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.snake.implimetations.data.local.db.dao.UserTableDao;
import com.snake.implimetations.data.model.db.UserTable;


@Database(entities = {UserTable.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserTableDao lockUserTableDao();

}

