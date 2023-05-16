package com.example.storyapp2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "comment",
        foreignKeys = {
                @ForeignKey(entity = Story.class,
                        parentColumns = "idStory",
                        childColumns = "id_story",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Account.class,
                        parentColumns = "id",
                        childColumns = "id_account",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class Comment {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCmt")
    private int idCmt;

    @ColumnInfo(name = "id_story")
    private int idStory;

    @ColumnInfo(name = "id_account")
    private int idAccount;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "comment")
    private String comment;

    public Comment(int idStory, int idAccount, String username, String comment) {
        this.idStory = idStory;
        this.idAccount = idAccount;
        this.username = username;
        this.comment = comment;
    }

    public int getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(int idCmt) {
        this.idCmt = idCmt;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
