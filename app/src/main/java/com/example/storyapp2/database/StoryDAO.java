package com.example.storyapp2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.storyapp2.model.Story;

import java.util.List;

@Dao
public interface StoryDAO {

    @Query("SELECT * FROM story ORDER BY idStory DESC")
    List<Story> getListStory();

    @Query("SELECT * FROM story WHERE idCategory = :idCategory ORDER BY idStory DESC")
    List<Story> getListStoryByID(int idCategory);

    @Query("SELECT * FROM story WHERE title = :title")
    List<Story> checkStory(String title);

    @Insert()
    void insertStory(Story story);

    @Delete
    void deleteStory(Story story);

    @Query("DELETE FROM story WHERE idCategory = :idCategory")
    void deleteStoryByID(int idCategory);

}
