package com.example.storyapp2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storyapp2.model.Story;

import java.util.List;

@Dao
public interface StoryDAO {

    @Query("SELECT * FROM story")
    List<Story> getListStory();

    @Query("SELECT * FROM story WHERE idCategory = :idCategory")
    List<Story> getListStoryByID(int idCategory);

    @Insert()
    void insertStory(Story story);

    @Update
    void updateStory(Story story);

    @Delete
    void deleteStory(Story story);

//    @Query("DELETE FROM story WHERE idStory = :idStory")
//    void delete(int idStory);
}
