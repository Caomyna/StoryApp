package com.example.storyapp2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.storyapp2.model.Account;

import java.util.List;

@Dao
public interface AccountDAO {

    @Query("SELECT * FROM account")
    List<Account> getListAccount();

    @Insert()
    void insertAccount(Account account);

    @Update
    void updateAccount(Account account);

    @Delete
    void deleteAccount(Account account);
}
