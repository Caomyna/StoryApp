package com.example.storyapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.AdapterStoryUser;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.FragmentStoryUserBinding;
import com.example.storyapp2.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoryUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoryUserFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private Integer mParam1;
//    private String mParam2;

    private int idCategory;
    private String nameCategory;

    private AdapterStoryUser adapterStoryUser;
    private List<Story> listStory;
    private RecyclerView recyclerView;

    private FragmentStoryUserBinding binding;
    private static final String TAG = "STORIES_USER_TAG";

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
            Log.d("StoryUserFragment", "getArguments() is not null");
            idCategory = getArguments().getInt("idCategory");
            nameCategory = getArguments().getString("nameCategory");
        } else {
            Log.d("StoryUserFragment", "getArguments() is null");
        }

        adapterStoryUser = new AdapterStoryUser(getContext(),listStory);
        adapterStoryUser.setOnItemClickListener(new AdapterStoryUser.OnItemClickListener() {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoryUserBinding.inflate(LayoutInflater.from(getContext()), container, false);
        Log.d(TAG,"onCreateView: Category: "+nameCategory);

        //hiển thị list truyện theo thể loại
        loadStoryByCategory(idCategory);

        return binding.getRoot();
    }

    private void loadStoryByCategory(int idCategory) {

        listStory = new ArrayList<>();
        recyclerView = binding.storiesRv;
        listStory = StoryAppDatabase.getInstance(getContext()).storyDAO().getListStoryByID(idCategory);
        adapterStoryUser = new AdapterStoryUser(getContext(),listStory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterStoryUser);
        adapterStoryUser.setData(listStory);

    }

}