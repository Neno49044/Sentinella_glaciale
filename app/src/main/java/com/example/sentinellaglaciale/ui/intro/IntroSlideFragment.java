package com.example.sentinellaglaciale.ui.intro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sentinellaglaciale.R;

public class IntroSlideFragment extends Fragment {

    private static final String ARG_IMAGE_RES = "image_res";
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";

    public static IntroSlideFragment newInstance(@DrawableRes int imageRes, String title, String description) {
        IntroSlideFragment fragment = new IntroSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES, imageRes);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro_slide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.intro_image);
        TextView titleView = view.findViewById(R.id.intro_title);
        TextView descriptionView = view.findViewById(R.id.intro_description);

        if (getArguments() != null) {
            int imageRes = getArguments().getInt(ARG_IMAGE_RES);
            String title = getArguments().getString(ARG_TITLE);
            String description = getArguments().getString(ARG_DESCRIPTION);

            imageView.setImageResource(imageRes);
            titleView.setText(title);
            descriptionView.setText(description);
        }
    }
}