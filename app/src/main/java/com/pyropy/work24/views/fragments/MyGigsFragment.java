package com.pyropy.work24.views.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.views.adapters.GeneralGigAdapter;
import com.pyropy.work24.views.adapters.MyGigsAdapter;

import java.util.ArrayList;

public class MyGigsFragment extends Fragment {

    RecyclerView gigRecycle;
    GeneralGigAdapter adapter ;
    View GigView;
    FirebaseUtil mUtil;
    private LinearLayoutManager mRvManager;
    ArrayList<Integer> bgs;

    public MyGigsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GigView = inflater.inflate(R.layout.fragment_my_gigs, container, false);

        initComponents(GigView);
        return GigView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateBgs();
    }

    private void populateBgs() {
        bgs = new ArrayList<>();
        bgs.add(R.drawable.roseana);
        bgs.add(R.drawable.purple_love);
        bgs.add(R.drawable.scooter);
        bgs.add(R.drawable.lush);
    }

    public void initComponents(View view){
        gigRecycle = (RecyclerView) view.findViewById(R.id.general_gig_cycle);
        mRvManager = new LinearLayoutManager(view.getContext());
        adapter = new GeneralGigAdapter(view.getContext(), bgs);

    }

    private void populateMyGigs() {

        gigRecycle.setLayoutManager(mRvManager);

        gigRecycle.setAdapter(adapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        populateMyGigs();
    }

}