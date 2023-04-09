package com.example.storyapp2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityCategoryAddBinding;
import com.example.storyapp2.model.Category;

public class CategoryAddActivity extends AppCompatActivity {

    //view binding
    private ActivityCategoryAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private String nameCategory = "";
    private void validateData() {
        //lấy dữ liệu
        nameCategory = binding.categoryEt.getText().toString().trim();
        if (TextUtils.isEmpty(nameCategory)) {
            Toast.makeText(this, "Vui lòng nhập thể loại...", Toast.LENGTH_SHORT).show();
        }else{
            Category category = new Category(nameCategory);
            StoryAppDatabase.getInstance(this).categoryDAO().insertCategory(category);
            Toast.makeText(this, "Add category successfully ...", Toast.LENGTH_SHORT).show();

            binding.categoryEt.setText("");

        }

    }
}