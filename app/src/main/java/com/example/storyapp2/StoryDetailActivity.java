package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.adapter.StoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityStoryDetailBinding;
import com.example.storyapp2.model.Favorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoryDetailActivity extends AppCompatActivity {

    private ActivityStoryDetailBinding binding;
    private boolean fav;
    private ImageView ivFavorite;
    private List<Favorite> favList;
    private Favorite story_fav;
    private StoryAdapter storyAdapter;
    private int idStory, idAccount;
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

            binding.titleTv.setText(title);
            binding.authorTv.setText(author);
            binding.contentTv.setText(content);
            Picasso.get().load(image).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(binding.imageIv);

        }

        //kiểm tra người dùng đã lưu chưa
        ivFavorite = findViewById(R.id.favoriteBtn);
        idStory = bundle.getInt("idStory");
        idAccount = StoryAppDatabase.user_current.getId();
        story_fav = new Favorite(idStory,idAccount);

        fav = isFavorite(idStory,idAccount);
        if (fav) {
            ivFavorite.setImageResource(R.drawable.ic_favorite);
        }else {
            ivFavorite.setImageResource(R.drawable.ic_favorite_border);
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
                    StoryAppDatabase.getInstance(StoryDetailActivity.this).favDAO().deleteFavoriteStory(idStory, idAccount);
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite);
                    fav = true;
                    StoryAppDatabase.getInstance(StoryDetailActivity.this).favDAO().insertStoryFav(story_fav);
                }
            }
        });

        //CommentBtn
        binding.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoryDetailActivity.this, CommentActivity.class);
                intent.putExtra("idStory", idStory);
                startActivity(intent);
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

    private boolean isFavorite(int idStory, int idAccount) {
        favList = StoryAppDatabase.getInstance(this).favDAO().checkIsFavorite(idStory, idAccount);
        return favList != null && !favList.isEmpty();
    }

}