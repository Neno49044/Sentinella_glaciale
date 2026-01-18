package com.example.sentinellaglaciale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sentinellaglaciale.database.User;
import com.example.sentinellaglaciale.database.UserDao;
import com.example.sentinellaglaciale.databinding.ActivityMainBinding;
import com.example.sentinellaglaciale.ui.Ghiacciaio;
import com.example.sentinellaglaciale.ui.GhiacciaioRepository;
import com.example.sentinellaglaciale.ui.mappa.DettagliGhiacciaioFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                updateFavoriteGlacierInMenu();
            }
        };
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
            int itemId = item.getItemId();
            if (itemId == R.id.nav_logout) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_favorite) {
                Ghiacciaio preferito = GhiacciaioRepository.getInstance().getPreferito();
                if (preferito != null) {
                    Bundle args = new Bundle();
                    args.putSerializable("ghiacciaio", preferito);
                    DettagliGhiacciaioFragment fragment = new DettagliGhiacciaioFragment();
                    fragment.setArguments(args);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                drawerLayout.closeDrawers();
                return true;
            }
            return false;
        });

        loadInitialUserData();
    }

    private void loadInitialUserData() {
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String loggedUserEmail = prefs.getString("logged_user_email", null);

        if (loggedUserEmail != null) {
            executor.execute(() -> {
                UserDao userDao = new UserDao(this);
                User user = userDao.getUserByEmail(loggedUserEmail);

                handler.post(() -> {
                    if (user != null) {
                        updateNavHeader(user);
                        updateNavMenu(user);
                        updateFavoriteGlacierInMenu(); // Also update on initial load
                    }
                });
            });
        }
    }

    private void updateNavHeader(User user) {
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
    }

    private void updateNavMenu(User user) {
        Menu menu = navigationView.getMenu();
        MenuItem emailItem = menu.findItem(R.id.nav_email);
        emailItem.setTitle(user.getEmail());
    }

    private void updateFavoriteGlacierInMenu() {
        Ghiacciaio preferito = GhiacciaioRepository.getInstance().getPreferito();
        Menu menu = navigationView.getMenu();
        MenuItem favoriteItem = menu.findItem(R.id.nav_favorite);

        if (favoriteItem != null) {
            if (preferito != null) {
                favoriteItem.setTitle("Ghiacciaio preferito");
            } else {
                favoriteItem.setTitle("Nessun ghiacciaio selezionato");
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