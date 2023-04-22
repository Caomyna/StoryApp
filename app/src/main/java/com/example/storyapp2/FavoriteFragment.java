package com.example.storyapp2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.StoryAdapter;
import com.example.storyapp2.model.Story;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private String title, author, image, content;
    private  int idCategory;

    private FavoriteFragment context;
    private RecyclerView rv_fav;
    private List<Story> listFav = new ArrayList<>();
    private StoryAdapter storyAdapter;
    private Story st;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String title, String author, String content, String image, int idCategory) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("author", author);
        args.putString("content", content);
        args.putString("image", image);
        args.putInt("idCategory", idCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            author = getArguments().getString("author");
            content = getArguments().getString("content");
            image = getArguments().getString("image");
            idCategory = getArguments().getInt("idCategory");

            Log.d("title",title);
            Log.d("author",author);
//        Log.d("content",);
            Log.d("image",image);
            Log.d("idCategory", String.valueOf(idCategory));
            st = new Story(title, author, content, image, idCategory);
            listFav.add(st);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv_fav = view.findViewById(R.id.favorite_stories);

        //hiển thị dữ liệu lên
        storyAdapter = new StoryAdapter(getContext(),listFav);
        rv_fav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_fav.setAdapter(storyAdapter);
        storyAdapter.setData(listFav);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }
}