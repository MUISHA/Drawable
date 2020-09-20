package com.example.drawable;

public class Upload {
    private String mName_full,mMail,mPhone,mPassword,mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String mail,String phone, String password, String imageUrl) {
        if (name.trim().equals("")) { name = "No Name";}
        if (mail.trim().equals("")) { mail = "No mail";}
        if (phone.trim().equals("")) { phone = "No mail";}
        if (password.trim().equals("")) { password = "No mail";}
        mName_full = name;
        mMail = mail;
        mPhone = phone;
        mPassword = password;
        mImageUrl = imageUrl;
    }

    public String getmName_full() {
        return mName_full;
    }

    public void setmName_full(String mName_full) {
        this.mName_full = mName_full;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}