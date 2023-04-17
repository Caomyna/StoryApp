package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.storyapp2.adapter.AdapterStoryUser;
import com.example.storyapp2.adapter.ViewPagerAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityDashboardUserBinding;
import com.example.storyapp2.model.Category;
import com.example.storyapp2.model.Story;

import java.util.ArrayList;
import java.util.List;

public class DashboardUserActivity extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    private List<Category> listCategory;
    private AdapterStoryUser adapterStoryUser;
    private List<Story> listStory;
    private RecyclerView recyclerView;
    private
    @NonNull ActivityDashboardUserBinding binding;


    public DashboardUserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViewAdapter(binding.viewPager);
//        getListStory();
        //handle click, logout
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(DashboardUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupViewAdapter(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);

        listCategory = new ArrayList<>();
        listCategory = StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();

        //Load from database
        for (Category category : listCategory) {

            viewPagerAdapter.addFragment(new StoryUserFragment(), category.getNameCategory());

        }
        viewPager.setAdapter(viewPagerAdapter);
        binding.tablayout.setupWithViewPager(binding.viewPager);

    }


}
