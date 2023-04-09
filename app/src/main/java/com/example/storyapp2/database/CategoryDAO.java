package com.example.storyapp2.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storyapp2.model.Category;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM category")
    Cursor getListCategory();
//    List<Account> getListAccount();

    @Insert()
    void insertCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);
}
