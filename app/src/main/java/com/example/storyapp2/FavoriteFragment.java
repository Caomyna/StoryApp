package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.StoryAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.model.Favorite;
import com.example.storyapp2.model.Story;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private StoryAdapter favAdapter;
    private List<Favorite> favList;
    private RecyclerView recyclerView;
    private View view;
    private List<Story> storyList;
    private int idAccount;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        favAdapter = new StoryAdapter(getContext(),storyList);
        favAdapter.setOnItemClickListener(new StoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Story story = storyList.get(position);
                Intent intent = new Intent(getActivity(), StoryDetailActivity.class);
                int idStory = story.getIdStory();
                String title = story.getTitle();
                String author = story.getAuthor();
                String content = story.getContent();
                String image = story.getImage();

                intent.putExtra("idStory", idStory);
                intent.putExtra("title",title);
                intent.putExtra("author",author);
                intent.putExtra("content",content);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        //Hiển thị danh sách đã lưu
        idAccount = StoryAppDatabase.user_current.getId();
        showSavedNews(idAccount);

        return view;
    }

    private void showSavedNews(int idAccount) {
        storyList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.favorite_stories);

        favList = StoryAppDatabase.getInstance(getContext()).favDAO().getListFavStory(idAccount);
        for (Favorite favorite: favList) {
            Story story = StoryAppDatabase.getInstance(getContext()).storyDAO().getListFavStory(favorite.getIdStory());
            storyList.add(story);
        }
        favAdapter = new StoryAdapter(getContext(),storyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favAdapter);
        favAdapter.setData(storyList);
    }
}