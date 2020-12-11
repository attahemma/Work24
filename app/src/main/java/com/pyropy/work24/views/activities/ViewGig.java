package com.pyropy.work24.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pyropy.work24.R;
import com.pyropy.work24.database.GigHelper;
import com.pyropy.work24.views.fragments.GettingStarted1;
import com.pyropy.work24.views.fragments.GettingStarted2;
import com.pyropy.work24.views.fragments.GettingStarted3;
import com.pyropy.work24.views.fragments.GigImageOne;
import com.pyropy.work24.views.fragments.GigImageThree;
import com.pyropy.work24.views.fragments.GigImageTwo;

public class ViewGig extends AppCompatActivity {

    private static final int NUM_PAGES = 3;
    String gigImage1Url, gigImage2Url, gigImage3Url, author, gigTitle, gigPrice, gigDescription;
    private TextView mTvGigTitle, mTvGigPrice, mTvGigDescription, mTvGigAuthor;
    public GigImageSliderAdapter mSliderAdapter;
    private ViewPager mPager2;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gig);
        Intent intent = getIntent();
        GigHelper gig = intent.getParcelableExtra("Gig");

        initComponents();
        prepDisplay(gig);
    }

    private void prepDisplay(GigHelper uGig) {
        //initializing strings with gig properties
        author = "By: " + uGig.author;
        gigImage1Url = uGig.img1Uri;
        gigImage2Url = uGig.img2Uri;
        gigImage3Url = uGig.img3Uri;
        gigTitle = uGig.getGigTitle();
        gigPrice = "$ " + uGig.getPrice();
        gigDescription = uGig.getDescription();

        //setting values to display components
        mTvGigTitle.setText(gigTitle);
        mTvGigPrice.setText(gigPrice);
        mTvGigAuthor.setText(author);
        mTvGigDescription.setText(gigDescription);
        mCollapsingToolbarLayout.setTitle(gigTitle);

        //initiating viewpager2 for gig images slides
        mSliderAdapter = new ViewGig.GigImageSliderAdapter(getSupportFragmentManager());
        mPager2.setAdapter(mSliderAdapter);
    }

    private void initComponents() {
        mTvGigTitle = findViewById(R.id.tv_title);
        mTvGigPrice = findViewById(R.id.tv_price);
        mTvGigDescription = findViewById(R.id.tv_description);
        mTvGigAuthor = findViewById(R.id.tv_author);
        mPager2 = findViewById(R.id.gig_img_pager);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_bar);
    }

    private class GigImageSliderAdapter extends FragmentStatePagerAdapter {

        public GigImageSliderAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //Fragment uFragment;
            switch (position){
                case 0:
                    GigImageOne image1  = new GigImageOne(gigImage1Url);
                    return image1;
                case 1:
                    GigImageTwo image2 = new GigImageTwo(gigImage2Url);
                    return image2;
                case 2:
                    GigImageThree image3 = new GigImageThree(gigImage3Url);
                    return image3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}