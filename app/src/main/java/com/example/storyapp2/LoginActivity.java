package com.example.storyapp2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.StoryAppDatabase;
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
        Cursor cursor = StoryAppDatabase.getInstance(this).accountDAO().getListAccount();

        while(cursor.moveToNext())
        {

            //lấy dữ liệu và gán vào biến
            String emailDB = cursor.getString(2);
            String passwordDB = cursor.getString(3);
            int roleDB = cursor.getInt(4);

            if (emailDB.equals(email) && passwordDB.equals(password) && roleDB == 0) {
                // lấy dữ liệu phân quyền và id
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                int role = cursor.getInt(4);

                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DashboardUserActivity.class);

                intent.putExtra("id",id);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password",password);
                intent.putExtra("role", role);

                startActivity(intent);

            }else if (emailDB.equals(email) && passwordDB.equals(password) && roleDB == 1){
                // lấy dữ liệu phân quyền và id
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                int role = cursor.getInt(4);

                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);

                intent.putExtra("id",id);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("password",password);
                intent.putExtra("role", role);

                startActivity(intent);
            }else {
                Toast.makeText(this, "Incorrect email or password....", Toast.LENGTH_SHORT).show();
            }
        }

        cursor.moveToFirst();
        cursor.close();

    }
}