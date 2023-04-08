package com.example.storyapp2;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.AccountDAO;
import com.example.storyapp2.database.AccountDatabase;
import com.example.storyapp2.databinding.ActivityLoginBinding;
import com.example.storyapp2.model.Account;
import com.example.storyapp2.model.AccountAdapter;

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

        //để lấy dữ liệu, gọi tới getListAccount() để lấy tất cả account trong database
        AccountDAO accountDAO = AccountDatabase.getInstance(this).accountDAO();
        listAccount = accountDAO.getListAccount();

        for (Account account : listAccount){
            String emailDB = String.valueOf(listAccount.get(2));
            String passwordDB = String.valueOf(listAccount.get(3));

            if (emailDB.equals(email) && passwordDB.equals(password)) {
                // lấy dữ liệu phân quyền và id

            }
        }
        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();


    }
}