package com.example.sentinellaglaciale.ui.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sentinellaglaciale.LoginActivity;
import com.example.sentinellaglaciale.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private IntroAdapter adapter;
    private TabLayout tabLayout;
    private Button skipButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the intro has been seen before
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        if (prefs.getBoolean("intro_seen", false)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return; // Skip the rest of the method
        }

        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        skipButton = findViewById(R.id.button_skip);
        nextButton = findViewById(R.id.button_next);

        // Create the fragments for the slides
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(IntroSlideFragment.newInstance(R.drawable.ic_sentinella, getString(R.string.benvenuto_in_sentinella_glaciale), getString(R.string.un_app_per_osservare_comprendere_e_proteggere_i_ghiacciai_delle_nostre_montagne)));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_mappa, getString(R.string.esplora_i_ghiacciai_sulla_mappa), getString(R.string.tocca_un_pin_per_scoprire_lo_stato_di_un_ghiacciaio_la_sua_evoluzione_nel_tempo_e_le_informazioni_principali)));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_ed, getString(R.string.impara_cosa_sta_succedendo_ai_ghiacciai), getString(R.string.scopri_le_cause_dello_scioglimento_le_conseguenze_sul_territorio_e_perch_il_cambiamento_climatico_ci_riguarda_tutti)));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_quiz, getString(R.string.mettiti_alla_prova), getString(R.string.quiz_semplici_e_interattivi_per_imparare_divertendosi_pensati_anche_per_i_pi_piccoli)));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_ev, getString(R.string.partecipa_e_fai_la_differenza), getString(R.string.scopri_eventi_incontri_e_iniziative_per_contribuire_attivamente_alla_salvaguardia_dei_ghiacciai)));
        adapter = new IntroAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        // Collega i pallini al ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();

        // Gestione bottoni
        skipButton.setOnClickListener(v -> finishIntro());
        nextButton.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < adapter.getItemCount() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                finishIntro();
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == adapter.getItemCount() - 1) {
                    nextButton.setText(R.string.fine);
                } else {
                    nextButton.setText(R.string.avanti2);
                }
            }
        });
    }

    private void finishIntro() {
        // Set the flag to true so the intro is not shown again
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("intro_seen", true);
        editor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}