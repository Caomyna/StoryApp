package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.databinding.ActivityStoryDetailBinding;
import com.squareup.picasso.Picasso;

public class StoryDetailActivity extends AppCompatActivity {

    private ActivityStoryDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //nhận dữ liệu
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");
        String image = intent.getStringExtra("image");
        int type = getIntent().getIntExtra("KEY", 0);
        final boolean[] fav = {getIntent().getBooleanExtra("FAV", false)};


        binding.titleTv.setText(title);
        binding.authorTv.setText(author);
        binding.contentTv.setText(content);
        Picasso.get().load(image).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(binding.imageIv);

        //favorite button
        binding.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(StoryDetailActivity.this, androidx.appcompat.R.anim.abc_fade_in));

                if (fav[0]) {

                    binding.favoriteBtn.setImageResource(R.drawable.ic_favorite_border);

                    fav[0] = false;

                } else {

                    binding.favoriteBtn.setImageResource(R.drawable.ic_favorite);
                    fav[0] = true;

                }

            }
        });

    }
}