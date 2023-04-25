package com.example.storyapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.storyapp2.model.Story;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private String title, author, image, content, email;
    private  int idCategory;

    private FavoriteFragment context;
    private RecyclerView rv_fav;
    private List<Story> listStory;
    private Story st;

    public FavoriteFragment() {
        // Required empty public constructor
    }


//    // TODO: Rename and change types and number of parameters
//    public static FavoriteFragment newInstance(String title, String author, String content, String image, int idCategory) {
//        FavoriteFragment fragment = new FavoriteFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        args.putString("author", author);
//        args.putString("content", content);
//        args.putString("image", image);
//        args.putInt("idCategory", idCategory);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            author = getArguments().getString("author");
            content = getArguments().getString("content");
            image = getArguments().getString("image");
            email = getArguments().getString("email");
        }
/*
        favAdapter = new FavAdapter(getContext(),listFav);
        favAdapter.setOnItemClickListener(new StoryAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Story story = listStory.get(position);
                Intent intent = new Intent(getContext(), StoryDetailActivity.class);
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
        });

 */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv_fav = view.findViewById(R.id.favorite_stories);

        //hiển thị dữ liệu lên
//        loadData();
//        listFav = StoryAppDatabase.getInstance(getContext()).favoriteDAO().getFavoriteStories(email);
//        favAdapter = new FavAdapter(getContext(),listFav);
//        rv_fav.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rv_fav.setAdapter(favAdapter);
//        favAdapter.setData(listFav);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

//    private void loadData() {
//        listFav = StoryAppDatabase.getInstance(getContext()).favoriteDAO().getFavoriteStories(email);
//        favAdapter.setData(listFav);
//
//    }
}