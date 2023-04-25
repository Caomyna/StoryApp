package com.example.storyapp2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.CategoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityDashboardAdminBinding;
import com.example.storyapp2.model.Category;

import java.util.List;

public class DashboardAdminActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private List<Category> listCategory;
    private RecyclerView recyclerView;
    private ActivityDashboardAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryAdapter = new CategoryAdapter(listCategory, new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClickItemCategory(int id) {
                onClickShowStory(id);
            }

            @Override
            public void deleteCategory(Category category) {
                clickDeleteCategory(category);
            }
        });

        listCategory = StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();
        categoryAdapter.setData(listCategory);
        recyclerView = binding.categoryRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryAdapter);

        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(DashboardAdminActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //handle click add Category
        binding.addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, CategoryAddActivity.class);
                startActivity(intent);
            }
        });

        //add story
        binding.addStoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdminActivity.this, StoryAddActivity.class);
                startActivity(intent);
            }
        });

        //search
        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                //được gọi khi người dùng gõ từng chữ cái
                try {
                    categoryAdapter.getFilter().filter(s);
                }catch (Exception e){

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void onClickShowStory(int idCategory) {
        Intent intent = new Intent(this, AdminListStoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("idCategory",idCategory);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadData(){
        listCategory = StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();
        categoryAdapter.setData(listCategory);
    }

    //handel click delete
    private void clickDeleteCategory(final Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Delete").setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //xóa
                        StoryAppDatabase.getInstance(DashboardAdminActivity.this).storyDAO().deleteStoryByID(category.getIdCategory());
                        StoryAppDatabase.getInstance(DashboardAdminActivity.this).categoryDAO().deleteCategory(category);
                        loadData();
                        Toast.makeText(DashboardAdminActivity.this, "Delete successfully...", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Hủy",null)
                .show();

    }
}