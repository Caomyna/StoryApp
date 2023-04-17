package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.databinding.ActivityStoryDetailBinding;

public class StoryDetailActivity extends AppCompatActivity {

    private ActivityStoryDetailBinding binding;
    int idStory;
    String title, author, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get data
        Intent intent = getIntent();
        idStory = intent.getIntExtra("idStory", idStory);

    }
}