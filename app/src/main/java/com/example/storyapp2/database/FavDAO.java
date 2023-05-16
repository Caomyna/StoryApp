package com.example.storyapp2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.storyapp2.model.Favorite;

import java.util.List;

@Dao
public interface FavDAO {

    @Query("SELECT * FROM favorite WHERE id_account = :idUser")
    List<Favorite> getListFavStory(int idUser);
    //
    @Query("SELECT * FROM favorite WHERE id_story= :idStory AND id_account= :idAccount")
    List<Favorite> checkIsFavorite(int idStory, int idAccount);

    @Insert
    void insertStoryFav(Favorite favorite);

    @Query("DELETE FROM favorite WHERE id_story= :idStory AND id_account= :idAccount")
    void deleteFavoriteStory(int idStory, int idAccount);
}
