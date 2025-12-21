package com.example.sentinellaglaciale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sentinellaglaciale.database.User;
import com.example.sentinellaglaciale.database.UserDao;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.appbar.MaterialToolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sentinellaglaciale.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar
        setSupportActionBar(binding.toolbar);

        // Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView navView = binding.navView;
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_educazione,
                R.id.navigation_mappa,
                R.id.navigation_eventi
        )
                .setOpenableLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(
                this,
                R.id.nav_host_fragment_activity_main
        );

        NavigationUI.setupActionBarWithNavController(
                this,
                navController,
                appBarConfiguration
        );

        NavigationUI.setupWithNavController(navView, navController);


        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_logout) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            }
            return false;
        });

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String loggedUserEmail = prefs.getString("logged_user_email", null);

        if (loggedUserEmail != null) {
            UserDao userDao = new UserDao(this);
            User user = userDao.getUserByEmail(loggedUserEmail);

            if (user != null) {
                navigationView = findViewById(R.id.navigation_view);

                View headerView = navigationView.getHeaderView(0);

                ImageView imgProfile = headerView.findViewById(R.id.imgProfile);
                TextView etUsername = headerView.findViewById(R.id.etUsername);

                etUsername.setText(user.getUsername());

                int avatarResId = getResources().getIdentifier(user.getImageUri(), "drawable", getPackageName());
                if (avatarResId != 0) {
                    imgProfile.setImageResource(avatarResId);
                } else {
                    imgProfile.setImageResource(R.drawable.ic_profile_placeholder);
                }
                Menu menu = navigationView.getMenu();
                MenuItem emailItem = menu.findItem(R.id.nav_email);
                emailItem.setTitle(user.getEmail());
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(
                this,
                R.id.nav_host_fragment_activity_main
        );
        return NavigationUI.navigateUp(
                navController,
                drawerLayout
        ) || super.onSupportNavigateUp();
    }
}
