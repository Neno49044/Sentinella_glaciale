package com.example.sentinellaglaciale;

import android.os.Bundle;
import android.widget.*;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sentinellaglaciale.database.UserDao;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etUsername, etPassword;
    ImageView imgProfile;
    Button btnRegister;

    // Array degli avatar predefiniti
    private final int[] avatars = {
            R.drawable.img_profilo_prova,
            R.drawable.images2,
            //R.drawable.avatar3,
            //R.drawable.avatar4
    };

    private int selectedAvatarResId = R.drawable.img_profilo_prova; // avatar di default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        imgProfile = findViewById(R.id.imgProfile);
        btnRegister = findViewById(R.id.btnRegister);

        // Imposta avatar di default
        imgProfile.setImageResource(selectedAvatarResId);

        // Click sull'immagine per scegliere avatar
        imgProfile.setOnClickListener(v -> showAvatarSelection());

        // Click sul bottone registra
        btnRegister.setOnClickListener(v -> register());
    }

    // Mostra AlertDialog con scelta avatar
    private void showAvatarSelection() {
        CharSequence[] avatarNames = {"Avatar 1", "Avatar 2", "Avatar 3", "Avatar 4"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scegli il tuo avatar");
        builder.setItems(avatarNames, (dialog, which) -> {
            selectedAvatarResId = avatars[which];
            imgProfile.setImageResource(selectedAvatarResId);
        });
        builder.show();
    }

    // Metodo per registrare l'utente
    private void register() {
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Salviamo il nome della risorsa nel DB
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

