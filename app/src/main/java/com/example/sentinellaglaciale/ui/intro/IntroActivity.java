package com.example.sentinellaglaciale.ui.intro;

import android.content.Intent;
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
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        skipButton = findViewById(R.id.button_skip);
        nextButton = findViewById(R.id.button_next);

        // Create the fragments for the slides
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(IntroSlideFragment.newInstance(R.drawable.ic_sentinella, "Benvenuto in Sentinella Glaciale!", "Un’app per osservare, comprendere e proteggere i ghiacciai delle nostre montagne"));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_mappa, "Esplora i ghiacciai sulla mappa", "Tocca un pin per scoprire lo stato di un ghiacciaio, la sua evoluzione nel tempo e le informazioni principali"));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_ed, "Impara cosa sta succedendo ai ghiacciai", "Scopri le cause dello scioglimento, le conseguenze sul territorio e perché il cambiamento climatico ci riguarda tutti"));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_quiz, "Mettiti alla prova!", "Quiz semplici e interattivi per imparare divertendosi, pensati anche per i più piccoli"));
        fragments.add(IntroSlideFragment.newInstance(R.drawable.screen_ev, "Partecipa e fai la differenza", "Scopri eventi, incontri e iniziative per contribuire attivamente alla salvaguardia dei ghiacciai"));

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
                    nextButton.setText("FINE");
                } else {
                    nextButton.setText("AVANTI");
                }
            }
        });
    }

    private void finishIntro() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}