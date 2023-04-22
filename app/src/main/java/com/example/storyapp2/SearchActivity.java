package com.example.storyapp2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.StoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivitySearchBinding;
import com.example.storyapp2.model.Story;

import java.util.List;

public class SearchActivity extends AppCompatActivity{

    private ActivitySearchBinding binding;
    private List<Story> storyList, arrayList;
    private StoryAdapter storyAdapter;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //hiển thị list stories
        recyclerView = binding.searchStories;
        storyList = StoryAppDatabase.getInstance(this).storyDAO().getListStory();

        storyAdapter = new StoryAdapter(this, storyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(storyAdapter);
        storyAdapter.setData(storyList);


        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        //search
        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                storyAdapter.getFilter().filter(editable);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (storyAdapter != null) {
            storyAdapter.release();
        }
    }
}