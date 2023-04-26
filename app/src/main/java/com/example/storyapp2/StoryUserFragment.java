package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.StoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryUserFragment extends Fragment {
    private int idCategory;
    private String nameCategory;
    private boolean fav;

    private StoryAdapter storyAdapter;
    private List<Story> listStory;
    private RecyclerView recyclerView;
    private View view;
    private ImageView ivFavorite;
    private static final String TAG = "STORIES_USER_TAG";
    private static final int MY_REQUEST = 100;

    public StoryUserFragment() {
        // Required empty public constructor
    }

    public static StoryUserFragment newInstance(int idCategory, String nameCategory) {
        StoryUserFragment fragment = new StoryUserFragment();
        Bundle args = new Bundle();
        args.putInt("idCategory", idCategory);
        args.putString("nameCategory", nameCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            idCategory = getArguments().getInt("idCategory");
            nameCategory = getArguments().getString("nameCategory");
            fav = getArguments().getBoolean("isOn",false);
        } else {
            Log.d("StoryUserFragment", "getArguments() is null");
        }

        storyAdapter = new StoryAdapter(getContext(),listStory);
        storyAdapter.setOnItemClickListener(new StoryAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Story story = listStory.get(position);
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                String title = story.getTitle();
                String author = story.getAuthor();
                String content = story.getContent();
                String image = story.getImage();

                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("content",content);
                intent.putExtra("image",image);
                intent.putExtra("fav", fav);
                startActivity(intent);

            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_story_user, container, false);
        Log.d(TAG,"onCreateView: Category: "+nameCategory);

        //hiển thị list truyện theo thể loại
        loadStoryByCategory(idCategory);

        //favBtn
        ivFavorite = view.findViewById(R.id.favorite);
        return view;
    }

    private void loadStoryByCategory(int idCategory) {

        listStory = new ArrayList<>();
        recyclerView = view.findViewById(R.id.storiesRv);

        listStory = StoryAppDatabase.getInstance(getContext()).storyDAO().getListStoryByID(idCategory);
        storyAdapter = new StoryAdapter(getContext(),listStory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(storyAdapter);
        storyAdapter.setData(listStory);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (storyAdapter != null) {
            storyAdapter.release();
        }
    }

}