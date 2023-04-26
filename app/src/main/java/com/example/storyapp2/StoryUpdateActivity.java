package com.example.storyapp2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityStoryUpdateBinding;
import com.example.storyapp2.model.Category;
import com.example.storyapp2.model.Story;

import java.util.List;

public class StoryUpdateActivity extends AppCompatActivity {

    private ActivityStoryUpdateBinding binding;
    private Story story;
    private List<Category> listCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //backBtn
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //click pick category
        binding.categoryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickCategory();
            }
        });

        //set du lieu
        story = (Story) getIntent().getExtras().get("object_story");
        if(story != null){
            binding.titleEt.setText(story.getTitle());
            binding.authorEt.setText(story.getAuthor());
            binding.contentEt.setText(story.getContent());
            binding.imageEt.setText(story.getImage());
        }

        //updateBtn
        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataToUpdate();
            }
        });
    }

    private void pickCategory() {
        listCategory =StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();

        String[] categoryArray = new String[listCategory.size()];
        for (int i = 0; i < listCategory.size(); i++) {
            categoryArray[i] = listCategory.get(i).getNameCategory();
        }

        //alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Category").setItems(categoryArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //handle item click from list
                String nameCategory = categoryArray[i];
                binding.categoryTv.setText(nameCategory);
                Log.d("nameCategory", nameCategory);
            }
        }).show();
    }

    private void getDataToUpdate() {
        //lấy dữ liệu
        String title = binding.titleEt.getText().toString().trim();
        String author = binding.authorEt.getText().toString().trim();
        String image = binding.imageEt.getText().toString().trim();
        String content = binding.contentEt.getText().toString().trim();
        String nameCategory = binding.categoryTv.getText().toString().trim();
        Log.d("nameCategory: ", nameCategory);


        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(image) || TextUtils.isEmpty(author) || TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin...", Toast.LENGTH_SHORT).show();
        } else {
            //lấy idcategory
            listCategory = StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();
            for (Category category : listCategory) {
                if (nameCategory.equals(category.getNameCategory())) {
                    int idCategory = category.getIdCategory();
                    Log.d("idCategory ", String.valueOf(idCategory));
                    updateStory(title, author, content, image, idCategory);
                }
            }
        }
    }
    private void updateStory(String title, String author, String content, String image, int idCategory) {
        //update
        story.setTitle(title);
        story.setAuthor(author);
        story.setContent(content);
        story.setImage(image);
        story.setIdCategory(idCategory);

        StoryAppDatabase.getInstance(this).storyDAO().updateStory(story);
        Toast.makeText(this, "Update story successfully ...", Toast.LENGTH_SHORT).show();

        binding.titleEt.setText("");
        binding.authorEt.setText("");
        binding.contentEt.setText("");
        binding.imageEt.setText("");
        binding.categoryTv.setText("");

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }

}