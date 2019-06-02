package com.example.grassroots.model.petition;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;

import java.io.Serializable;
import java.util.List;

public class PetitionViewModel extends ViewModel implements Serializable {
    private String mPetitionName;
    private String mPetitionSupporter;
    private String mPetitionDescription;
    private Uri mPetitionImage;
    private int mPetitionSignature;
    private int mPetitionSignatureGoal;
    private String petitionKey;
    private List<Petition> mPetitionList;
    private List<PetitionUpdates> mPetitionUpdatesList;

    public void setmPetitionList(List<Petition> mPetitionList) {
        this.mPetitionList = mPetitionList;
    }

    public List<PetitionUpdates> getmPetitionUpdatesList() {
        return mPetitionUpdatesList;
    }

    public void setmPetitionUpdatesList(List<PetitionUpdates> mPetitionUpdatesList) {
        this.mPetitionUpdatesList = mPetitionUpdatesList;
    }

    public void setPetitionList(List<Petition> petition){
        this.mPetitionList =petition;
    }
    public List<Petition> getmPetitionList(){
        return mPetitionList;
    }

    public String getPetitionKey() {
        return petitionKey;
    }

    public void setPetitionKey(String petitionKey) {
        this.petitionKey = petitionKey;
    }

    private PetitionUpdates petitionUpdates=new PetitionUpdates();

    public PetitionUpdates getPetitionUpdates() {
        return  petitionUpdates;
    }
    public void setPetitionUpdates(PetitionUpdates petitionUpdates) {
        this.petitionUpdates = petitionUpdates;
    }

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

