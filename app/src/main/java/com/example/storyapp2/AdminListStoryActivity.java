package com.example.storyapp2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.AdminStoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityAdminListStoryBinding;
import com.example.storyapp2.model.Story;

import java.util.List;

public class AdminListStoryActivity extends AppCompatActivity {
    private ActivityAdminListStoryBinding binding;
    private RecyclerView recyclerView;
    private List<Story> listStory;
    private AdminStoryAdapter adminStoryAdapter;
    private Story story;
    private StoryAppDatabase storyAppDatabase;
    private ImageView editBtn, deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminListStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, backBtn
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //hiển thị list stories
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        int idCategory = bundle.getInt("idCategory");
        recyclerView = binding.listStoryAdmin;
        listStory = StoryAppDatabase.getInstance(this).storyDAO().getListStoryByID(idCategory);

        adminStoryAdapter = new AdminStoryAdapter(listStory, new AdminStoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Story story = listStory.get(position);
                Intent intent = new Intent(AdminListStoryActivity.this, StoryDetailActivity.class);
                String title = story.getTitle();
                String author = story.getAuthor();
                String content = story.getContent();
                String image = story.getImage();

                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("content",content);
                intent.putExtra("image",image);
                startActivity(intent);
            }

            @Override
            public void onEditItem(int position) {

            }

            @Override
            public void onDeleteItem(Story story) {
                onClickDelete(story);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adminStoryAdapter);
        adminStoryAdapter.setData(listStory);

    }

    private void onClickDelete(final Story story) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete").setMessage("Bạn có chắc chắn muốn xóa?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //xóa
                        StoryAppDatabase.getInstance(AdminListStoryActivity.this).storyDAO().deleteStory(story);
                        loadData();
                        Toast.makeText(AdminListStoryActivity.this, "Delete successfully...", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy",null)
                .show();
    }

    private void loadData() {
        listStory = StoryAppDatabase.getInstance(this).storyDAO().getListStory();
        adminStoryAdapter.setData(listStory);
    }
/*
    private void showStoryByID(int idCategory) {
        recyclerView = binding.listStoryAdmin;
        listStory = StoryAppDatabase.getInstance(this).storyDAO().getListStoryByID(idCategory);

        adminStoryAdapter = new AdminStoryAdapter(listStory, new AdminStoryAdapter.OnItemClickListener() {
            @Override
            public void onEditItem(int position) {

            }

            @Override
            public void onDeleteItem(Story story) {
                onClickDelete(story);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adminStoryAdapter);
        adminStoryAdapter.setData(listStory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

 */
}