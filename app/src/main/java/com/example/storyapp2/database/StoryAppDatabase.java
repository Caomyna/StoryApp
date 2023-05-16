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
import com.example.storyapp2.model.Favorite;
import com.example.storyapp2.model.Story;

@Database(entities = {Story.class, Account.class, Category.class, Favorite.class}, version = 3, exportSchema = false)
public abstract class StoryAppDatabase extends RoomDatabase {

    static Migration migration_from_1_to_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'story' ADD COLUMN 'idCategory' INTEGER NOT NULL DEFAULT 0");
        }
    };

    static final Migration migration_from_2_to_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'favorite' ('idFav' INTEGER NOT NULL PRIMARY KEY, 'id_story' INTEGER NOT NULL,'id_account' INTEGER NOT NULL,FOREIGN KEY('id_account') REFERENCES 'account'('id') ON DELETE CASCADE, FOREIGN KEY('id_story') REFERENCES 'story'('idStory') ON DELETE CASCADE)");
        }
    };

    private static final String DATABASE_NAME = "storyapp.db";
    private static StoryAppDatabase instance;

    public static synchronized StoryAppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), StoryAppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(migration_from_1_to_2)
                    .addMigrations(migration_from_2_to_3)
                    .build();
        }
        return instance;
    }

    public static Account user_current = new Account();

    public abstract StoryDAO storyDAO();

    public abstract AccountDAO accountDAO();

    public abstract CategoryDAO categoryDAO();

    public abstract FavDAO favDAO();

}