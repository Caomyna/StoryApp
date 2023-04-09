package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.databinding.ActivityDashboardUserBinding;

public class DashboardUserActivity extends AppCompatActivity {

    private ActivityDashboardUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(DashboardUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}