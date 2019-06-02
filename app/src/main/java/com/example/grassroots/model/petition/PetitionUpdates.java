package com.example.grassroots.model.petition;

import android.net.Uri;

import java.io.Serializable;

public class PetitionUpdates  implements Serializable {
    private String mPetitionHeadLine;
    private String mPetitionDescription;
    private Uri mPetitionImageUri;
    private String mPetitionImageURL;

    public String getmPetitionHeadLine() {
        return mPetitionHeadLine;
    }

    public void setmPetitionHeadLine(String mPetitionHeadLine) {
        this.mPetitionHeadLine = mPetitionHeadLine;
    }

    public PetitionUpdates(){

    }

    public PetitionUpdates(String mPetitionHeadLine, String mPetitionDescription, String mPetitionImageURL) {
        this.mPetitionHeadLine=mPetitionHeadLine;
        this.mPetitionDescription = mPetitionDescription;
        this.mPetitionImageURL = mPetitionImageURL;
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
