package com.example.grassroots.model.petition;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;

public class PetitionViewModel extends ViewModel {
    private String mPetitionName;
    private String mPetitionSupporter;
    private String mPetitionDescription;
    private Uri mPetitionImage;
    private int mPetitionSignature;
    private int mPetitionSignatureGoal;

    public int getmPetitionSignatureGoal() {
        return mPetitionSignatureGoal;
    }

    public void setmPetitionSignatureGoal(int mPetitionSignatureGoal) {
        this.mPetitionSignatureGoal = mPetitionSignatureGoal;
    }

    public void setmPetitionSignature(int mPetitionSignature ){
        this.mPetitionSignature=mPetitionSignature;
    }
    public int getmPetitionSignature(){
        return mPetitionSignature;
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

    public Uri getmPetitionImage() {
        return mPetitionImage;
    }

    public void setmPetitionImage(Uri mPetitionImage) {
        this.mPetitionImage = mPetitionImage;
    }
}

