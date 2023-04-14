package com.example.storyapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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

    public StoryUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idCategory Parameter 1.
     * @param nameCategory Parameter 2.
     * @return A new instance of fragment StoryUserFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        return inflater.inflate(R.layout.fragment_story_user, container, false);
    }
}