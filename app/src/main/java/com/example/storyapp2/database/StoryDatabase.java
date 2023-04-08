package com.example.storyapp2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.storyapp2.model.Story;

@Database(entities = {Story.class}, version = 1, exportSchema = false)
public abstract class StoryDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "story.db";
    private static StoryDatabase instance;

    public static synchronized StoryDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), StoryDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StoryDAO storyDAO();

}
