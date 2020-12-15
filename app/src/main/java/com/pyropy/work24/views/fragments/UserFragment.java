package com.pyropy.work24.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.views.adapters.UsersAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserFragment extends Fragment {

    ViewGroup mViewGroup;
    RecyclerView userRecycle;
    UsersAdapter mAdapter;
    FirebaseUtil mUtil;
    private LinearLayoutManager mRvManager;

    public UserFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewGroup = (ViewGroup) inflater.inflate(R.layout.users, container, false);

        initComponents(mViewGroup);

        return mViewGroup;
    }

    private void initComponents(ViewGroup viewGroup) {
        userRecycle = (RecyclerView) viewGroup.findViewById(R.id.users_recycler);
        mRvManager = new LinearLayoutManager(viewGroup.getContext());
        mAdapter = new UsersAdapter(viewGroup.getContext());
    }

    private void populateUsers(){
        userRecycle.setLayoutManager(mRvManager);
        userRecycle.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        populateUsers();
    }
}
