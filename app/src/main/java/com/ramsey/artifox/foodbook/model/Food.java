package com.ramsey.artifox.foodbook.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Food implements Parcelable{

    private String mName;
    private String mWeight;
    private String mPrice;
    private String mDescription;
    private String mImageId;
    private String mCategoryId;

    public Food() {

    }

    public Food(String imageId, String name, String price, String weight, String description, String categoryId) {
        mImageId = imageId;
        mName = name;
        mPrice = price;
        mWeight = weight;
        mDescription = description;
        mCategoryId = categoryId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getImageId() {
        return mImageId;
    }

    public void setImageId(String imageId) {
        mImageId = imageId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
