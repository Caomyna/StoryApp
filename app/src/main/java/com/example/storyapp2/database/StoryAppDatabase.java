package com.example.storyapp2.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.storyapp2.model.Account;
import com.example.storyapp2.model.Category;
import com.example.storyapp2.model.Story;

@Database(entities = {Story.class, Account.class, Category.class}, version = 2)
public abstract class StoryAppDatabase extends RoomDatabase {

    static Migration migration_from_1_to_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'story' ADD COLUMN 'idCategory' INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final String DATABASE_NAME = "storyapp.db";
    private static StoryAppDatabase instance;

    public static synchronized StoryAppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), StoryAppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(migration_from_1_to_2)
                    .build();
        }
        return instance;
    }

    public abstract StoryDAO storyDAO();

    public abstract AccountDAO accountDAO();

    public abstract CategoryDAO categoryDAO();

}
