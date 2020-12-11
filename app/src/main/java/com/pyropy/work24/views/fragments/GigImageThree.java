package com.pyropy.work24.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pyropy.work24.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GigImageThree extends Fragment {
    ViewGroup root;
    ImageView gigImage3;
    String image3Url;

    public GigImageThree(String image3Url){
        this.image3Url = image3Url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.gig_image_three, container,false);

        gigImage3 = (ImageView) root.findViewById(R.id.gigimage3);
        //Glide.with(this).load(R.drawable.slide_one).into(imageView);
        //Glide.with(this).load(R.drawable.indicator_two).into(indicator);

        initComponents();

        return root;
    }

    private void initComponents() {
        Glide.with(this).load(image3Url).into(gigImage3);
    }
}
