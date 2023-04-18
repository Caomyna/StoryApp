package com.example.storyapp2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.storyapp2.adapter.AdapterStoryUser;
import com.example.storyapp2.adapter.ViewPagerAdapter;
import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityDashboardUserBinding;
import com.example.storyapp2.model.Category;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DashboardUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPagerAdapter viewPagerAdapter;
    private AdapterStoryUser adapterStoryUser;
    private List<Category> listCategory;
    private DrawerLayout drawerLayout;
    private SearchView searchView;
    private
    @NonNull ActivityDashboardUserBinding binding;




    public DashboardUserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViewAdapter(binding.viewPager);

        //nhận dữ liệu ở màn hình đăng nhập gửi
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");


        //thêm toolbar và bắt sự kiện
        setSupportActionBar(binding.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,binding.toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupViewAdapter(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);

        listCategory = new ArrayList<>();
        listCategory = StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            
        } else if (id == R.id.nav_favorite) {
            
        } else if (id == R.id.nav_logout) {

            View logoutBtn = findViewById(R.id.nav_logout);
            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Chuyển người dùng đến màn hình đăng nhập
                    Intent intent = new Intent(DashboardUserActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //nhấn vào nút back
    @Override
    public void onBackPressed() {
        //nếu drawer mở
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            //nếu drawer đóng thì thoát app
            super.onBackPressed();
        }
    }

    //Search


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterStoryUser.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterStoryUser.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
