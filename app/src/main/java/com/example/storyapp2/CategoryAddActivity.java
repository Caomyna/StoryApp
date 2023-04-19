package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.adapter.CategoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityCategoryAddBinding;
import com.example.storyapp2.model.Category;

import java.util.List;

public class CategoryAddActivity extends AppCompatActivity {

    private List<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    //view binding
    private ActivityCategoryAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }
        });
    }


//    private String nameCategory = "";
    private void addCategory() {
        //lấy dữ liệu
        String nameCategory = binding.categoryEt.getText().toString().trim();
        if (TextUtils.isEmpty(nameCategory)) {
            Toast.makeText(this, "Vui lòng nhập thể loại...", Toast.LENGTH_SHORT).show();
        }else{
            nameCategory = binding.categoryEt.getText().toString().trim();
            Category category = new Category(nameCategory);

            if(isCategoryExist(category)){
                //da ton tai
                Toast.makeText(this, "Category existed...", Toast.LENGTH_SHORT).show();
                return;
            }else {
                StoryAppDatabase.getInstance(this).categoryDAO().insertCategory(category);
                Toast.makeText(this, "Add category successfully ...", Toast.LENGTH_SHORT).show();

                binding.categoryEt.setText("");

                Intent intent = new Intent(CategoryAddActivity.this, DashboardAdminActivity.class);
                intent.putExtra("nameCategory", nameCategory);
                startActivity(intent);
            }

        }

    }

    private boolean isCategoryExist(Category category){
        List<Category> list = StoryAppDatabase.getInstance(this).categoryDAO().checkCategory(category.getNameCategory());
        return list != null && !list.isEmpty();
    }
}