package com.example.storyapp2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "story")
public class Story implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idStory")
    private int idStory;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "idCategory")
    private int idCategory;

    public Story(String title, String author, String content, String image, int idCategory) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.image = image;
        this.idCategory = idCategory;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {

        this.idCategory = idCategory;
    }
}