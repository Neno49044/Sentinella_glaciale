package com.example.sentinellaglaciale.ui.educazione;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sentinellaglaciale.R;
import com.example.sentinellaglaciale.databinding.FragmentQuizBinding;

public class QuizFragment extends Fragment {

    private @NonNull FragmentQuizBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
            QuizViewModel QuizViewModel =
                    new ViewModelProvider(this).get(QuizViewModel.class);

            binding = FragmentQuizBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            final TextView textView = binding.textQuiz;
            QuizViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

            var view = inflater.inflate(R.layout.fragment_educazione, container, false);
            Button button = (Button) view.findViewById(R.id.button_educazione);
            binding.buttonQuiz.setOnClickListener(v ->
                    NavHostFragment.findNavController(QuizFragment.this)
                            .navigate(R.id.action_QuizFragment_to_educazioneFragment)
            );

            return root;
            //return inflater.inflate(R.layout.fragment_quiz, container, false);


}}