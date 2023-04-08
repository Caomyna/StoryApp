package com.example.storyapp2.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.storyapp2.model.Account;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "account.db";
    private static AccountDatabase instance;

    public static synchronized AccountDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AccountDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract AccountDAO accountDAO();
}
