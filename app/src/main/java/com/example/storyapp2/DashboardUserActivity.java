package com.example.storyapp2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityDashboardUserBinding;
import com.google.android.material.navigation.NavigationView;

public class DashboardUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private
    @NonNull ActivityDashboardUserBinding binding;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVORITE =1;
    private int currentFragment = FRAGMENT_HOME;

    public DashboardUserActivity() {
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //thêm toolbar và bắt sự kiện
        setSupportActionBar(binding.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,binding.toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //navigation
        binding.navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());

        //hiển thị email trong header
        View headerView = binding.navigationView.getHeaderView(0);
        TextView info = headerView.findViewById(R.id.info);
        info.setText(StoryAppDatabase.user_current.getName());

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            if (currentFragment != FRAGMENT_HOME) {
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        } else if (id == R.id.nav_favorite) {
            if (currentFragment != FRAGMENT_FAVORITE) {
                replaceFragment(new FavoriteFragment());
                currentFragment = FRAGMENT_FAVORITE;
            }

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

    // Nạp menu vào ActionBar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(DashboardUserActivity.this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
