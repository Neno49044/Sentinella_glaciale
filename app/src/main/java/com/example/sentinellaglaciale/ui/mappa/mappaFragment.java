package com.example.sentinellaglaciale.ui.mappa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sentinellaglaciale.databinding.FragmentMappaBinding;
import com.example.sentinellaglaciale.databinding.FragmentMappaBinding;

public class mappaFragment extends Fragment {

    private FragmentMappaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mappaViewModel mappaViewModel =
                new ViewModelProvider(this).get(mappaViewModel.class);

        binding = FragmentMappaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMappa;
        mappaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}