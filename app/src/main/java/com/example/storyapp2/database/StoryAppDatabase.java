package com.example.storyapp2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.storyapp2.model.Account;
import com.example.storyapp2.model.Category;
import com.example.storyapp2.model.Story;

@Database(entities = {Story.class, Account.class, Category.class}, version = 1, exportSchema = false)
public abstract class StoryAppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "storyapp.db";
    private static StoryAppDatabase instance;

    public static synchronized StoryAppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), StoryAppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StoryDAO storyDAO();

    public abstract AccountDAO accountDAO();

    public abstract CategoryDAO categoryDAO();

}
