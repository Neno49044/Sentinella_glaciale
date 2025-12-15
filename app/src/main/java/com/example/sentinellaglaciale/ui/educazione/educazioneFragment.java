package com.example.sentinellaglaciale.ui.educazione;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sentinellaglaciale.R;
import com.example.sentinellaglaciale.databinding.FragmentEducazioneBinding;
import com.google.android.material.snackbar.Snackbar;

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

        var view = inflater.inflate(R.layout.fragment_educazione, container, false);

        Button button = (Button) view.findViewById(R.id.button_educazione);
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