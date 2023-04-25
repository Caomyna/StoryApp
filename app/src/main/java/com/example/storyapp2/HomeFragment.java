package com.example.storyapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.storyapp2.adapter.ViewPagerAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.FragmentHomeBinding;
import com.example.storyapp2.model.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPagerAdapter viewPagerAdapter;
    private List<Category> listCategory;
    private FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        setupViewAdapter(binding.viewPager);

        return binding.getRoot();
    }

    private void setupViewAdapter(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        listCategory = new ArrayList<>();
        listCategory = StoryAppDatabase.getInstance(getContext()).categoryDAO().getListCategory();

        //Load from database
        for (Category category : listCategory) {
            //Tạo fragment mới
            StoryUserFragment fragment = new StoryUserFragment();

            viewPagerAdapter.addFragment(fragment, category.getNameCategory());

            Bundle bundle = new Bundle();
            bundle.putInt("idCategory", category.getIdCategory());
            bundle.putString("nameCategory", category.getNameCategory());

            // Đưa bundle vào fragment
            fragment.setArguments(bundle);
        }
        viewPager.setAdapter(viewPagerAdapter);
        binding.tablayout.setupWithViewPager(binding.viewPager);

    }

}