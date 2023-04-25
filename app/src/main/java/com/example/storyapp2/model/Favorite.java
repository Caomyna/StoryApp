//package com.example.storyapp2.model;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "favorite")
//public class Favorite {
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    private int id;
//
//    @ColumnInfo(name = "title")
//    private String title;
//
//    @ColumnInfo(name = "author")
//    private String author;
//
//    @ColumnInfo(name = "content")
//    private String content;
//
//    @ColumnInfo(name = "image")
//    private String image;
//
//    @ColumnInfo(name = "emailUser ")
//    private String emailUser;
//
//    public Favorite(String title, String author, String content, String image, String emailUser) {
//        this.title = title;
//        this.author = author;
//        this.content = content;
//        this.image = image;
//        this.emailUser = emailUser;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getEmailUser() {
//        return emailUser;
//    }
//
//    public void setEmailUser(String emailUser) {
//        this.emailUser = emailUser;
//    }
//}