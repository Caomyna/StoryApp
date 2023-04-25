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
    private boolean fav;
    private static final int MY_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //nhận dữ liệu
        final Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");
        String image = intent.getStringExtra("image");
        String email = intent.getStringExtra("email");
//        fav = intent.getBooleanExtra("fav", false);

        binding.titleTv.setText(title);
        binding.authorTv.setText(author);
        binding.contentTv.setText(content);
        Picasso.get().load(image).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(binding.imageIv);

        //favorite button
        /*
        Favorite storyFav = new Favorite(title,author, content, image, email);
        for (Favorite favorite: listFav) {
            if (favorite == storyFav) {
                fav = true;
            }else {
                fav = false;
            }
        }

         */
        binding.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(StoryDetailActivity.this, androidx.appcompat.R.anim.abc_fade_in));

                if (fav) {
                    fav = false;
                } else {
                    fav = true;
                }
                Intent intent = new Intent();
                intent.putExtra("fav", fav);
                setResult(RESULT_OK, intent);
            }
        });

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent();
                intent.putExtra("fav", fav);
                setResult(RESULT_OK, intent);
            }
        });

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
//        if(requestCode == MY_REQUEST) {
//
//            // resultCode được set bởi DetailActivity
//            // RESULT_OK chỉ ra rằng kết quả này đã thành công
//            if(resultCode == RESULT_OK) {
//                // Nhận dữ liệu từ Intent trả về
//                boolean fav_result = data.getIntExtra("fav", fav);
//                // Sử dụng kết quả result bằng cách hiện Toast
//                Toast.makeText(this, "Result: " + fav_result, Toast.LENGTH_LONG).show();
//            } else {
//                // DetailActivity không thành công, không có data trả về.
//            }
//        }
//    }
}