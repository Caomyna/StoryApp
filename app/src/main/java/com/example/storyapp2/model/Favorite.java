package com.example.storyapp2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite",
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
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idFav")
    private int idFav;

    @ColumnInfo(name = "id_story")
    private int idStory;

    @ColumnInfo(name = "id_account")
    private int idAccount;

    public Favorite(int idStory, int idAccount) {
        this.idStory = idStory;
        this.idAccount = idAccount;
    }

    public int getIdFav() {
        return idFav;
    }

    public void setIdFav(int idFav) {
        this.idFav = idFav;
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
}
