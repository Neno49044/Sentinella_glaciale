package com.example.sentinellaglaciale;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.sentinellaglaciale.database.UserDao;

public class RegisterActivity extends BaseActivity {

    EditText etEmail, etUsername, etPassword;
    ImageView imgProfile;
    LinearLayout layoutProfilePicker;
    Button btnRegister;
    ImageView btnBack;

    private final int[] avatars = {
            R.drawable.ic_sentinella,
            R.drawable.sentinella2,
            R.drawable.sentinella3,
            R.drawable.sentinella4
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

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        layoutProfilePicker.setOnClickListener(v -> showAvatarSelection());

        btnRegister.setOnClickListener(v -> register());
    }

    private void showAvatarSelection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.scegli_la_tua_immagine_profilo2);

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
            Toast.makeText(this, R.string.compila_tutti_i_campi, Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedAvatarResId == -1) {
            Toast.makeText(this, R.string.scegli_un_immagine_profilo, Toast.LENGTH_SHORT).show();
            return;
        }

        String avatarName = getResources().getResourceEntryName(selectedAvatarResId);

        UserDao userDao = new UserDao(this);
        boolean success = userDao.registerUser(email, username, password, avatarName);

        if (success) {
            Toast.makeText(this, R.string.registrazione_completata, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, R.string.email_gi_registrata, Toast.LENGTH_SHORT).show();
        }
    }
}
