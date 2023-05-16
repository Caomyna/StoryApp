package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.CommentAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityCommentBinding;
import com.example.storyapp2.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private ActivityCommentBinding binding;
    private int idStory, idAccount;
    private String comment, username;
    private List<Comment> commentList;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //handle click, go back
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Send Comment
        binding.sendCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendComment();
            }
        });

        //hiển thị comment
        Bundle bundle = getIntent().getExtras();
        idStory = bundle.getInt("idStory");
        showComment(idStory);
        Log.d("ID STORY: ", String.valueOf(idStory));
    }

    private void showComment(int idStory) {

        recyclerView = binding.commentRV;
        commentList = new ArrayList<>();
        commentList = StoryAppDatabase.getInstance(this).commentDAO().getListComment(idStory);
        commentAdapter = new CommentAdapter(CommentActivity.this,commentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        commentAdapter.setData(commentList);
        recyclerView.setAdapter(commentAdapter);

    }

    private void sendComment() {
        Intent intent = getIntent();
        idStory = intent.getIntExtra("idStory", idStory);
        idAccount = StoryAppDatabase.user_current.getId();
        username = StoryAppDatabase.user_current.getName();
        comment = binding.cmtEd.getText().toString().trim();

        Comment cmt = new Comment(idStory,idAccount,username, comment);
        StoryAppDatabase.getInstance(this).commentDAO().sendComment(cmt);
        commentList = StoryAppDatabase.getInstance(this).commentDAO().getListComment(idStory);
        commentAdapter.setData(commentList);
        binding.cmtEd.setText("");
    }

}