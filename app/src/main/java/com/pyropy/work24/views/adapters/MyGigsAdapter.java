package com.pyropy.work24.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.database.GigHelper;
import com.pyropy.work24.views.activities.ViewGig;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyGigsAdapter extends RecyclerView.Adapter<MyGigsAdapter.MyGigViewHolder> {

    Context mContext;
    FirebaseUtil mUtil;
    DatabaseReference mReference;
    ChildEventListener mListener;
    ArrayList<GigHelper> mygigs;

    public MyGigsAdapter(Context context){
        mContext = context;
        mygigs = new ArrayList<>();
        mUtil = FirebaseUtil.getInstances(mContext);
        mListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                GigHelper uHelper = dataSnapshot.getValue(GigHelper.class);
                Log.d("UserGigs:", uHelper.gigTitle);
                uHelper.setId(dataSnapshot.getKey());
                mygigs.add(uHelper);
                notifyItemInserted(mygigs.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                GigHelper uHelper = dataSnapshot.getValue(GigHelper.class);
                Log.d("UserGigs:", uHelper.gigTitle);
                uHelper.setId(dataSnapshot.getKey());
                mygigs.add(uHelper);
                notifyItemInserted(mygigs.size()-1);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mUtil.mDbRef.child("user-gigs").child(mUtil.mAuthEmail).addChildEventListener(mListener);
    }

    @NonNull
    @Override
    public MyGigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_profile_recycle_item,parent,false);
        return new MyGigViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGigViewHolder holder, int position) {
        GigHelper uHelper = mygigs.get(position);
        holder.bind(uHelper, mContext);
    }

    @Override
    public int getItemCount() {
        return mygigs.size();
    }

    public class MyGigViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView gigImg;
        TextView gigTitle,price;

        public MyGigViewHolder(@NonNull View itemView) {
            super(itemView);

            gigImg = (ImageView) itemView.findViewById(R.id.ivThumb);
            gigTitle = (TextView) itemView.findViewById(R.id.txt_title);
            price =  (TextView) itemView.findViewById(R.id.price);

            itemView.setOnClickListener(this);
        }

        public void bind(GigHelper helper, Context context){
            String title = helper.getGigTitle();
            String uprice = "$ "+helper.getPrice();
            price.setText(uprice);
            StringBuilder shortenedTitle = new StringBuilder();
            if (title.length() > 10) {
                shortenedTitle.append(title.substring(0, 7));
                shortenedTitle.append("...");
            }else{
                shortenedTitle.append(title);
            }
            gigTitle.setText(shortenedTitle.toString());

            showImage(helper.getImg1Uri(), context);
        }

        public void showImage(String url, Context context){
            Glide.with(context).load(url).into(gigImg);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("ITEM CLICKED", String.valueOf(position));
            GigHelper selectedGig = mygigs.get(position);
            Intent intent = new Intent(view.getContext(), ViewGig.class);
            intent.putExtra("Gig",selectedGig);
            view.getContext().startActivity(intent);
        }
    }
}
