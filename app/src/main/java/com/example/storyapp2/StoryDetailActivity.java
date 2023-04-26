package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.databinding.ActivityStoryDetailBinding;
import com.squareup.picasso.Picasso;

public class StoryDetailActivity extends AppCompatActivity {

    private ActivityStoryDetailBinding binding;
    private boolean fav;
    private ImageView ivFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //nhận dữ liệu
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString("title");
            String author = bundle.getString("author");
            String content = bundle.getString("content");
            String image = bundle.getString("image");
            fav = getIntent().getBooleanExtra("fav", false);

            binding.titleTv.setText(title);
            binding.authorTv.setText(author);
            binding.contentTv.setText(content);
            Picasso.get().load(image).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(binding.imageIv);

        }

        //Fav Btn
        ivFavorite = findViewById(R.id.favoriteBtn);
        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(StoryDetailActivity.this, androidx.appcompat.R.anim.abc_fade_in));
                if (fav) {
                    ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                    fav = false;
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite);
                    fav = true;
                }
                Intent intent = new Intent();
                intent.putExtra("isOn", fav);
                Log.d("isOn", String.valueOf(fav));
                setResult(RESULT_OK, intent);
            }
        });

        //back Btn
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
/*
    private void setHeart() {

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(StoryDetailActivity.this, androidx.appcompat.R.anim.abc_fade_in));
                listStory = StoryAppDatabase.getInstance(StoryDetailActivity.this).storyDAO().getListStory();
                if (fav) {
                    ivFavorite.setImageResource(R.drawable.ic_favorite_border);
                    fav = false;
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite);
                    fav = true;
                }
                Intent intent = new Intent();
                intent.putExtra("isOn", fav);
                setResult(RESULT_OK, intent);
            }
        });

    }

 */
}