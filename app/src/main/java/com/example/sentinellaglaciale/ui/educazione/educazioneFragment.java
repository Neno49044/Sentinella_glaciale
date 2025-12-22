package com.example.sentinellaglaciale.ui.educazione;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sentinellaglaciale.R;
import com.example.sentinellaglaciale.databinding.FragmentEducazioneBinding;

public class educazioneFragment extends Fragment {

    private FragmentEducazioneBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        educazioneViewModel educazioneViewModel =
                new ViewModelProvider(this).get(educazioneViewModel.class);

        binding = FragmentEducazioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEducazione;
        educazioneViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        binding.buttonEducazione.setOnClickListener(v ->
                NavHostFragment.findNavController(educazioneFragment.this)
                        .navigate(R.id.action_educazioneFragment_to_QuizFragment)
        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}