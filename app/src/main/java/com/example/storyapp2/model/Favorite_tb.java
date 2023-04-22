package com.example.storyapp2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class Favorite_tb {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "is_favorite ")
    private int is_favorite ;


}