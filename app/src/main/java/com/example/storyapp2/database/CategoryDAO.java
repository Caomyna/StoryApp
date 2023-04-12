package com.example.storyapp2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storyapp2.model.Category;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM category")
    List<Category> getListCategory();

    @Insert()
    void insertCategory(Category category);

    @Query("SELECT * FROM category WHERE nameCategory = :nameCategory")
    List<Category> checkCategory(String nameCategory);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);


}
