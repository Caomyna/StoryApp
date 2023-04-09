package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.databinding.ActivityDashboardAdminBinding;

public class DashboardAdminActivity extends AppCompatActivity {

    //view binding
    private ActivityDashboardAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa dữ liệu người dùng liên quan đến phiên đăng nhập hiện tại
//                accountDatabase = AccountDatabase.getInstance(getApplicationContext());
//                AccountDAO accountDAO = accountDatabase.accountDAO();
//                accountDAO.

                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(DashboardAdminActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //handle click add
        binding.addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, CategoryAddActivity.class);
                startActivity(intent);
            }
        });
    }

    //handel click a
}