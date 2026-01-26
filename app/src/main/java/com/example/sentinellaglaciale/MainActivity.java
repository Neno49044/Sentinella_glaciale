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

import androidx.appcompat.app.AlertDialog;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(null);

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                updateFavoriteGlacierInMenu();
            }
        });

        BottomNavigationView navView = binding.navView;
        navView.setItemIconTintList(null);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_educazione,
                R.id.navigation_mappa,
                R.id.navigation_eventi,
                R.id.navigation_Quiz,
                R.id.navigation_dettagli
        )
                .setOpenableLayout(drawerLayout)
                .build();

        navController = Navigation.findNavController(
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
                executor.execute(() -> {
                    Ghiacciaio preferito = GhiacciaioRepository.getInstance().getPreferito();
                    handler.post(() -> {
                        if (preferito != null) {
                            Bundle args = new Bundle();
                            args.putSerializable("ghiacciaio", preferito);
                            navController.navigate(R.id.navigation_dettagli, args);
                        }
                        drawerLayout.closeDrawers();
                    });
                });
                return true;
            } else if (itemId == R.id.nav_language) {
                mostraDialogLingua();
                return true;
            }
            return false;
        });

        loadInitialUserData();
    }

    // --- METODI PER LA LINGUA ---
    private void mostraDialogLingua() {
        String[] lingue = {getString(R.string.italiano), getString(R.string.inglese)};

        new AlertDialog.Builder(this)
                .setTitle(R.string.scegli_lingua)
                .setItems(lingue, (dialog, which) -> {
                    if (which == 0) {
                        setLocale("it");
                    } else {
                        setLocale("en");
                    }
                })
                .show();
    }

    private void setLocale(String langCode) {
        // Salva la scelta
        getSharedPreferences("Settings", MODE_PRIVATE).edit().putString("My_Lang", langCode).apply();

        // Riavvia l'activity per applicare i cambiamenti
        recreate();
    }

    // --- FINE METODI LINGUA ---

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
                        updateFavoriteGlacierInMenu();
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
        if (emailItem != null) {
            emailItem.setTitle(user.getEmail());
        }
    }

    private void updateFavoriteGlacierInMenu() {
        executor.execute(() -> {
            Ghiacciaio preferito = GhiacciaioRepository.getInstance().getPreferito();
            handler.post(() -> {
                Menu menu = navigationView.getMenu();
                MenuItem favoriteItem = menu.findItem(R.id.nav_favorite);

                if (favoriteItem != null) {
                    if (preferito != null) {
                        favoriteItem.setTitle(getString(R.string.ghiacciaio_preferito) + preferito.getNome());
                    } else {
                        favoriteItem.setTitle(R.string.nessun_ghiacciaio_selezionato);
                    }
                }
            });
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
