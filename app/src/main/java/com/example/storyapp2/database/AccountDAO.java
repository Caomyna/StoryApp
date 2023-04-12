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
//    Cursor getListAccount();
    List<Account> getListAccount();

    @Query("SELECT * FROM account WHERE email= :email AND password= :password AND role = :role")
    List<Account> checkAccount(String email, String password, int role);

    @Insert()
    void insertAccount(Account account);

    @Update
    void updateAccount(Account account);

    @Delete
    void deleteAccount(Account account);


}
