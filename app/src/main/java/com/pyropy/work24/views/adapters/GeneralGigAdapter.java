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
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.database.GigHelper;
import com.pyropy.work24.views.activities.ViewGig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GeneralGigAdapter extends RecyclerView.Adapter<GeneralGigAdapter.GigViewHolder>{

    Context mContext;
    FirebaseUtil mUtil;
    ChildEventListener mListener;
    ArrayList<GigHelper> all_gigs;
    List<Integer> bgs;

    public GeneralGigAdapter(Context context, ArrayList<Integer> bgs){
        mContext = context;
        all_gigs = new ArrayList<>();
        this.bgs = bgs;
        mUtil = FirebaseUtil.getInstances(mContext);
        mListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                GigHelper uHelper = snapshot.getValue(GigHelper.class);
                Log.d("UserGigs:", uHelper.gigTitle);
                uHelper.setId(snapshot.getKey());
                all_gigs.add(uHelper);
                notifyItemInserted(all_gigs.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                GigHelper uHelper = snapshot.getValue(GigHelper.class);
                Log.d("UserGigs:", uHelper.gigTitle);
                uHelper.setId(snapshot.getKey());
                all_gigs.add(uHelper);
                notifyItemInserted(all_gigs.size()-1);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mUtil.mDbRef.child("Gigs").addChildEventListener(mListener);
    }

    @NonNull
    @Override
    public GigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View uView = LayoutInflater.from(mContext).inflate(R.layout.general_gig_item,parent,false);
        return new GigViewHolder(uView);
    }

    @Override
    public void onBindViewHolder(@NonNull GigViewHolder holder, int position) {
        GigHelper uHelper = all_gigs.get(position);
        holder.bind(uHelper, mContext);
    }

    @Override
    public int getItemCount() {
        return all_gigs.size();
    }

    public class GigViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView gigImg;
        TextView gigTitle, gigPrice, gigDesc;
        CardView gigView;

        public GigViewHolder(@NonNull View itemView) {
            super(itemView);


            gigView = (CardView) itemView.findViewById(R.id.gigCard);
            gigImg = (ImageView) itemView.findViewById(R.id.gigImg);
            gigTitle = (TextView) itemView.findViewById(R.id.gigTitle);
            gigPrice = (TextView) itemView.findViewById(R.id.price);
            gigDesc = (TextView) itemView.findViewById(R.id.tv_gig_desc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("ITEM CLICKED", String.valueOf(position));
            GigHelper selectedGig = all_gigs.get(position);
            Intent intent = new Intent(view.getContext(), ViewGig.class);
            intent.putExtra("Gig",selectedGig);
            view.getContext().startActivity(intent);
        }

        public void bind(GigHelper helper, Context context) {
            Random r = new Random();
            gigView.setBackgroundResource(bgs.get(r.nextInt(bgs.size())));
            String title = helper.getGigTitle();
            String price = "$ " + helper.getPrice();
            String desc = helper.getDescription();
            gigPrice.setText(price);
            gigTitle.setText(title);
            StringBuilder shortenedDesc = new StringBuilder();
            if(desc.length() > 70){
                shortenedDesc.append(desc.substring(0,67));
                shortenedDesc.append("...");
            }else{
                shortenedDesc.append(desc);
            }
            gigDesc.setText(shortenedDesc.toString());

            showImage(helper.getImg1Uri(), context);
        }

        private void showImage(String img1Uri, Context context) {
            Glide.with(context).load(img1Uri).into(gigImg);
        }
    }
}
