package com.example.storyapp2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.storyapp2.model.Comment;

import java.util.List;

@Dao
public interface CommentDAO {
    @Query("SELECT * FROM comment WHERE id_story = :idStory")
    List<Comment> getListComment(int idStory);

    @Insert
    void sendComment(Comment comment);


}
