package com.example.sentinellaglaciale.ui.educazione;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.lifecycle.ViewModelProvider;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
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

            ColorStateList verde = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked}
                    },
                    new int[]{
                            Color.GRAY,
                            Color.parseColor("#008000")
                    }
            );

            ColorStateList rosso = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked}
                    },
                    new int[]{
                            Color.GRAY,
                            Color.parseColor("#FF0000")
                    }
            );

            View view = inflater.inflate(R.layout.fragment_quiz, container, false);

            binding.buttonSoluzione.setOnClickListener(v -> {
                binding.textQuiz.setVisibility(GONE);
                binding.rbOpzione11.setEnabled(false);
                binding.rbOpzione11.setButtonTintList(rosso);
                binding.rbOpzione12.setEnabled(false);
                binding.rbOpzione12.setButtonTintList(verde);
                binding.rbOpzione13.setEnabled(false);
                binding.rbOpzione13.setButtonTintList(rosso);
                binding.rbOpzione14.setEnabled(false);
                binding.rbOpzione14.setButtonTintList(rosso);
                if(binding.rbOpzione12.isChecked()){
                    binding.solDomanda1.setTextColor(Color.parseColor("#008000"));
                }else{
                    binding.solDomanda1.setTextColor(Color.parseColor("#FF0000"));
                }
                binding.rbOpzione21.setEnabled(false);
                binding.rbOpzione21.setButtonTintList(rosso);
                binding.rbOpzione22.setEnabled(false);
                binding.rbOpzione22.setButtonTintList(rosso);
                binding.rbOpzione23.setEnabled(false);
                binding.rbOpzione23.setButtonTintList(verde);
                binding.rbOpzione24.setEnabled(false);
                binding.rbOpzione24.setButtonTintList(rosso);
                if(binding.rbOpzione23.isChecked()){
                    binding.solDomanda2.setTextColor(Color.parseColor("#008000"));
                }else{
                    binding.solDomanda2.setTextColor(Color.parseColor("#FF0000"));
                }
                binding.rbOpzione31.setEnabled(false);
                binding.rbOpzione31.setButtonTintList(rosso);
                binding.rbOpzione32.setEnabled(false);
                binding.rbOpzione32.setButtonTintList(rosso);
                binding.rbOpzione33.setEnabled(false);
                binding.rbOpzione33.setButtonTintList(rosso);
                binding.rbOpzione34.setEnabled(false);
                binding.rbOpzione34.setButtonTintList(verde);
                if(binding.rbOpzione34.isChecked()){
                    binding.solDomanda3.setTextColor(Color.parseColor("#008000"));
                }else{
                    binding.solDomanda3.setTextColor(Color.parseColor("#FF0000"));
                }
                binding.buttonSoluzione.setVisibility(GONE);
                binding.buttonQuizcompleto.setVisibility(VISIBLE);
                binding.solDomanda1.setVisibility(VISIBLE);
                binding.solDomanda2.setVisibility(VISIBLE);
                binding.solDomanda3.setVisibility(VISIBLE);
            });

        binding.buttonQuizcompleto.setOnClickListener(v ->
                NavHostFragment.findNavController(QuizFragment.this)
                        .navigate(R.id.action_QuizFragment_to_educazioneFragment)
        );


        return root;
            //return inflater.inflate(R.layout.fragment_quiz, container, false);

    }

}