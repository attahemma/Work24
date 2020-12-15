package com.pyropy.work24.database;

import android.os.Parcel;
import android.os.Parcelable;

public class UserHelper implements Parcelable {

    public String fullname,password,email,phone,usertype,display_img;

    public UserHelper() {
    }

    public UserHelper(String fullname, String password, String email, String phone, String usertype, String display_img) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.usertype = usertype;
        this.display_img = display_img;
    }

    protected UserHelper(Parcel in) {
        fullname = in.readString();
        password = in.readString();
        email = in.readString();
        phone = in.readString();
        usertype = in.readString();
        display_img = in.readString();
    }

    public static final Creator<UserHelper> CREATOR = new Creator<UserHelper>() {
        @Override
        public UserHelper createFromParcel(Parcel in) {
            return new UserHelper(in);
        }

        @Override
        public UserHelper[] newArray(int size) {
            return new UserHelper[size];
        }
    };

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getDisplay_img() {
        return display_img;
    }

    public void setDisplay_img(String display_img) {
        this.display_img = display_img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fullname);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(usertype);
        parcel.writeString(display_img);
    }
}
