package com.pyropy.work24.views.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.database.GigHelper;
import com.pyropy.work24.database.UserHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    Context mContext;
    FirebaseUtil mUtil;
    ChildEventListener mListener;
    ArrayList<UserHelper> all_users;
    Dialog myDialog;

    public UsersAdapter(Context context){
        mContext = context;
        all_users = new ArrayList<>();
        mUtil = FirebaseUtil.getInstances(mContext);
        mListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserHelper uHelper = dataSnapshot.getValue(UserHelper.class);
                Log.d("UserGigs:", uHelper.getEmail());
                all_users.add(uHelper);
                notifyItemInserted(all_users.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserHelper uHelper = dataSnapshot.getValue(UserHelper.class);
                Log.d("UserGigs:", uHelper.getEmail());
                all_users.add(uHelper);
                notifyItemInserted(all_users.size()-1);
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
        mUtil.mDbRef.child("Users").addChildEventListener(mListener);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View uView = LayoutInflater.from(mContext).inflate(R.layout.users_item,parent,false);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.user_details_pop_up);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return new UserViewHolder(uView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserHelper uUserHelper = all_users.get(position);
        holder.bind(uUserHelper,mContext);
    }

    @Override
    public int getItemCount() {
        return all_users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView userImg;
        TextView userFullName, userType, userTitle;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userImg = (ImageView) itemView.findViewById(R.id.user_thumb);
            userFullName = (TextView) itemView.findViewById(R.id.user_name);
            userTitle = (TextView) itemView.findViewById(R.id.user_description);
            userType = (TextView) itemView.findViewById(R.id.user_type);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TextView dialog_name = (TextView) myDialog.findViewById(R.id.dialog_name_id);
            TextView dialog_phone = (TextView) myDialog.findViewById(R.id.dialog_phone_id);
            TextView dialog_email = (TextView) myDialog.findViewById(R.id.dialog_email_id);

            dialog_name.setText(all_users.get(getAdapterPosition()).getFullname());
            dialog_phone.setText(all_users.get(getAdapterPosition()).getPhone());
            dialog_email.setText(all_users.get(getAdapterPosition()).getEmail());
            myDialog.show();
        }

        public void bind(UserHelper helper, Context context){
            String full_name = helper.getFullname();
            String user_description = helper.getEmail();
            String user_type = helper.usertype;

            userFullName.setText(full_name);
            userTitle.setText(user_description);
            userType.setText(user_type);
        }

        private void showImage(String url, Context context){

        }
    }
}
