package com.example.storyapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityCategoryUpdateBinding;
import com.example.storyapp2.model.Category;

public class CategoryUpdateActivity extends AppCompatActivity {

    private ActivityCategoryUpdateBinding binding;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //back Btn
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //updateBtn
        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCategory();
            }
        });

        //set du lieu
        category = (Category) getIntent().getExtras().get("object_category");
        if(category != null){
            binding.categoryEt.setText(category.getNameCategory());
        }
    }

    private void updateCategory() {

        //lấy dữ liệu
        String nameCategory = binding.categoryEt.getText().toString().trim();
        if (TextUtils.isEmpty(nameCategory)) {
            Toast.makeText(this, "Vui lòng nhập thể loại...", Toast.LENGTH_SHORT).show();
        }else{
            //update
            category.setNameCategory(nameCategory);

            StoryAppDatabase.getInstance(this).categoryDAO().updateCategory(category);
            Toast.makeText(this, "Update category successfully ...", Toast.LENGTH_SHORT).show();

            binding.categoryEt.setText("");

            Intent intentResult = new Intent();
            setResult(Activity.RESULT_OK, intentResult);
            finish();
        }
    }

}