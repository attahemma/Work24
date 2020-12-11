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

public class GigImageTwo extends Fragment {
    ViewGroup root;
    ImageView gigImage2;
    String image2Url;

    public GigImageTwo(String image2Url){
        this.image2Url = image2Url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.gig_image_two, container,false);

        gigImage2 = (ImageView) root.findViewById(R.id.gigimage2);
        //Glide.with(this).load(R.drawable.slide_one).into(imageView);
        //Glide.with(this).load(R.drawable.indicator_two).into(indicator);

        initComponents();

        return root;
    }

    private void initComponents() {
        Glide.with(this).load(image2Url).into(gigImage2);
    }
}
