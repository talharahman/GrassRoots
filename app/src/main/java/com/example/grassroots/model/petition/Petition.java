package com.example.grassroots.model.petition;

import android.net.Uri;

public class Petition {
    private String mPetitionName;
    private String mPetitionSupporter;
    private String mPetitionDescription;
    private Uri mPetitionImageUri;
    private String mPetitionImageURL;

    public Petition() {
        //empty constructor needed
    }

    public Petition( String petitionName, String petitionSupporter, String petitionDescription, String petitionImage) {
        mPetitionName =petitionName;
        mPetitionSupporter =petitionSupporter;
        mPetitionDescription =petitionDescription;
        mPetitionImageURL=petitionImage;
    }

    public String getmPetitionName() {
        return mPetitionName;
    }

    public void setmPetitionName(String mPetitionName) {
        this.mPetitionName = mPetitionName;
    }

    public String getmPetitionSupporter() {
        return mPetitionSupporter;
    }

    public void setmPetitionSupporter(String mPetitionSupporter) {
        this.mPetitionSupporter = mPetitionSupporter;
    }

    public String getmPetitionDescription() {
        return mPetitionDescription;
    }

    public void setmPetitionDescription(String mPetitionDescription) {
        this.mPetitionDescription = mPetitionDescription;
    }

    public Uri getmPetitionImageUri() {
        return mPetitionImageUri;
    }

    public void setmPetitionImageUri(Uri mPetitionImageUri) {
        this.mPetitionImageUri = mPetitionImageUri;
    }

    public String getmPetitionImageURL() {
        return mPetitionImageURL;
    }

    public void setmPetitionImageURL(String mPetitionImageURL) {
        this.mPetitionImageURL = mPetitionImageURL;
    }
}
