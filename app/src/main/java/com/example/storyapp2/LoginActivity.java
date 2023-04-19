package com.example.storyapp2;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.adapter.AccountAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityLoginBinding;
import com.example.storyapp2.model.Account;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private AccountAdapter accountAdapter;
    private List<Account> listAccount;

    //view binding
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, go to register screen
        binding.noAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //handle click, begin login
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private String email="", password = "";
    private void validateData() {
        //Trước khi tạo tài khoản, kiểm tra dữ liệu có hợp lệ k
        //get data

        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid email pattern...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show();
        }else{
            //bat dau login
            loginUser();
        }
    }

    private void loginUser() {
        //lấy dữ liệu đã nhập vào
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();

        Account accountUser = new Account(null,email, password, 0);
        Account accountAdmin = new Account(null,email, password, 1);

        if(isAccountExist(accountUser)){
            //da ton tai
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            //truyền dữ liệu
            Intent intent = new Intent(LoginActivity.this, DashboardUserActivity.class);
            startActivity(intent);
            return;
        } else if (isAccountExist(accountAdmin)) {
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, DashboardAdminActivity.class));
            return;
        }else {
            Toast.makeText(this, "Email or password is incorrect !!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private boolean isAccountExist(Account account){
        listAccount = StoryAppDatabase.getInstance(this).accountDAO().checkAccount(account.getEmail(), account.getPassword(), account.getRole());
        return listAccount != null && !listAccount.isEmpty();
    }
}