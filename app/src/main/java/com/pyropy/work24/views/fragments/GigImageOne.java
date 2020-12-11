package com.pyropy.work24.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pyropy.work24.R;
import com.pyropy.work24.views.activities.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GigImageOne extends Fragment{
    ViewGroup root;
    ImageView gigImage1;
    String image1Url;

    public GigImageOne(String image1Url){
        this.image1Url = image1Url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.gig_image_one, container,false);

        gigImage1 = (ImageView) root.findViewById(R.id.gigimage1);
        //Glide.with(this).load(R.drawable.slide_one).into(imageView);
        //Glide.with(this).load(R.drawable.indicator_two).into(indicator);

        initComponents();

        return root;
    }

    private void initComponents() {
        Glide.with(this).load(image1Url).into(gigImage1);
    }
}
