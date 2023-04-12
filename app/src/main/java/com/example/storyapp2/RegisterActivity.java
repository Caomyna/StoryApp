package com.example.storyapp2;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityRegisterBinding;
import com.example.storyapp2.model.Account;


public class RegisterActivity extends AppCompatActivity {

    //view binding
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //handle click, begin register
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private String name = "", email = "", password = "";
    int role = 0;

    private void validateData() {
        //Trước khi tạo tài khoản, kiểm tra dữ liệu có hợp lệ k
        //get data
        name = binding.edName.getText().toString().trim();
        email = binding.edEmail.getText().toString().trim();
        password = binding.edPassword.getText().toString().trim();
        String cPassword = binding.edcPassword.getText().toString().trim();

        //validate data
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter your name...", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email pattern...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cPassword)) {
            Toast.makeText(this, "Confirm Password", Toast.LENGTH_SHORT).show();
        } else if (!(password.equals(cPassword))) {
            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show();
        } else {
            //bat dau tao tai khoan
            createUserAccount();
        }
    }

    private void createUserAccount() {
        name = binding.edName.getText().toString().trim();
        email = binding.edEmail.getText().toString().trim();
        password = binding.edPassword.getText().toString().trim();

        Account account = new Account(name, email, password,role);

        StoryAppDatabase.getInstance(this).accountDAO().insertAccount(account);
        Toast.makeText(this, "Create account successfully", Toast.LENGTH_SHORT).show();

        binding.edName.setText("");
        binding.edEmail.setText("");
        binding.edPassword.setText("");
        binding.edcPassword.setText("");
    }
}
