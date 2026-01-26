package com.example.sentinellaglaciale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.sentinellaglaciale.ui.intro.IntroActivity;

public class PrivacyPolicyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. SCELTA LINGUA (Solo al primissimo avvio)
        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        if (!settings.getBoolean("lang_selected", false)) {
            showInitialLanguageDialog();
            return;
        }

        // 2. CONTROLLO PRIVACY
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        if (prefs.getBoolean("privacy_accepted", false)) {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
            return;
        }

        // 3. MOSTRA PRIVACY POLICY
        setContentView(R.layout.activity_privacy_policy);

        CheckBox checkboxAccept = findViewById(R.id.checkbox_accept);
        Button buttonAccept = findViewById(R.id.button_accept);
        Button buttonDecline = findViewById(R.id.button_decline);
        TextView privacyText = findViewById(R.id.privacy_text);

        // Rendi i link nel testo cliccabili
        privacyText.setMovementMethod(LinkMovementMethod.getInstance());

        checkboxAccept.setOnCheckedChangeListener((buttonView, isChecked) -> {
            buttonAccept.setEnabled(isChecked);
        });

        buttonAccept.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("privacy_accepted", true);
            editor.apply();

            startActivity(new Intent(this, IntroActivity.class));
            finish();
        });

        buttonDecline.setOnClickListener(v -> {
            finishAffinity();
        });
    }

    private void showInitialLanguageDialog() {
        String[] languages = {"Italiano", "English"};
        
        new AlertDialog.Builder(this)
                .setTitle("Scegli la lingua / Choose language")
                .setCancelable(false)
                .setItems(languages, (dialog, which) -> {
                    String langCode = (which == 0) ? "it" : "en";
                    
                    // Salva la scelta in "Settings" (usato da BaseActivity)
                    getSharedPreferences("Settings", MODE_PRIVATE).edit()
                            .putString("My_Lang", langCode)
                            .putBoolean("lang_selected", true)
                            .apply();
                    
                    // Ricarica l'activity per applicare la lingua
                    recreate();
                })
                .show();
    }
}
