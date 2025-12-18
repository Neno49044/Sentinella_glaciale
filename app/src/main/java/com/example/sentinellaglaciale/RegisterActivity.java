package com.example.sentinellaglaciale;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.sentinellaglaciale.database.UserDao;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etUsername, etPassword;
    ImageView imgProfile;
    LinearLayout layoutProfilePicker;
    Button btnRegister;


    private final int[] avatars = {
            R.drawable.ic_sentinella,
            R.drawable.sentinella2,
            R.drawable.sentinella3
    };

    private int selectedAvatarResId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        imgProfile = findViewById(R.id.imgProfile);
        layoutProfilePicker = findViewById(R.id.layoutProfilePicker);
        btnRegister = findViewById(R.id.btnRegister);


        layoutProfilePicker.setOnClickListener(v -> showAvatarSelection());

        btnRegister.setOnClickListener(v -> register());
    }

    private void showAvatarSelection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scegli la tua immagine profilo");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_avatar_grid, null);
        builder.setView(dialogView);

        GridView gridView = dialogView.findViewById(R.id.gridAvatars);

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return avatars.length;
            }

            @Override
            public Object getItem(int position) {
                return avatars[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {
                    imageView = (ImageView) LayoutInflater.from(RegisterActivity.this)
                            .inflate(R.layout.item_avatar, parent, false);
                } else {
                    imageView = (ImageView) convertView;
                }

                imageView.setImageResource(avatars[position]);
                return imageView;
            }
        };

        gridView.setAdapter(adapter);

        AlertDialog dialog = builder.create();

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            selectedAvatarResId = avatars[position];
            imgProfile.setImageResource(selectedAvatarResId);
            dialog.dismiss();
        });

        dialog.show();
    }


    // Registrazione utente
    private void register() {
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedAvatarResId == -1) {
            Toast.makeText(this, "Scegli un'immagine profilo", Toast.LENGTH_SHORT).show();
            return;
        }

        String avatarName = getResources().getResourceEntryName(selectedAvatarResId);

        UserDao userDao = new UserDao(this);
        boolean success = userDao.registerUser(email, username, password, avatarName);

        if (success) {
            Toast.makeText(this, "Registrazione completata", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Email già registrata", Toast.LENGTH_SHORT).show();
        }
    }
}

