package com.example.sentinellaglaciale.ui.eventi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sentinellaglaciale.databinding.FragmentEventiBinding;
import com.example.sentinellaglaciale.databinding.FragmentEventiBinding;

public class eventiFragment extends Fragment {

    private FragmentEventiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventiViewModel eventiViewModel =
                new ViewModelProvider(this).get(eventiViewModel.class);

        binding = FragmentEventiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEventi;
        eventiViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}