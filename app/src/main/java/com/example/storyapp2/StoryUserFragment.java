package com.example.storyapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp2.adapter.AdapterStoryUser;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.model.Story;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoryUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoryUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Integer mParam1;
    private String mParam2;

    private AdapterStoryUser adapterStoryUser;
    private List<Story> listStory;
    private RecyclerView recyclerView;

//    public StoryUserFragment() {
//        // Required empty public constructor
//    }

    public static StoryUserFragment newInstance(int idCategory, String nameCategory) {
        StoryUserFragment fragment = new StoryUserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idCategory);
        args.putString(ARG_PARAM2, nameCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_user, container, false);
        recyclerView = view.findViewById(R.id.storiesRv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapterStoryUser = new AdapterStoryUser(getContext(),listStory);
//        adapterStoryUser.setData(getListStory());
//        recyclerView.setAdapter(adapterStoryUser);
        //        listStory = new ArrayList<>();
        listStory = StoryAppDatabase.getInstance(getContext()).storyDAO().getListStory();
        adapterStoryUser = new AdapterStoryUser(getContext(),listStory);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterStoryUser);
        adapterStoryUser.setData(listStory);
        return view;
    }

}