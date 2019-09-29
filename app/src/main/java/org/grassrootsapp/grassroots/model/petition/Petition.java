package org.grassrootsapp.grassroots.model.petition;

import android.net.Uri;


import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Petition implements Serializable {
    private String mPetitionName;
    private String mPetitionSupporter;
    private String mPetitionDescription;
    private Uri mPetitionImageUri;
    private String mPetitionImageURL;
    private int mPetitionSignature;
    private int mPetitionSignatureGoal;
    private String petitionKey;
    private List<PetitionUpdates> mPetitionUpdatesList = new ArrayList();
    private List<String> signers = new ArrayList<>();
    private List<PetitionSignatures> petitionSignatures = new ArrayList<>();

    public List<PetitionSignatures> getPetitionSignatures() {
        return petitionSignatures;
    }
    //    private List<DocumentReference> petitionSignatures;
//
//    public List<DocumentReference> getPetitionSignatures() {
//        return petitionSignatures;
//    }


    public void addPetitionSignature(String signatureID, String timeSigned){
//        return new PetitionSignatures( signatureID,  timeSigned);
    }

    public List<String> getSigners() {
        return signers;
    }

    public void setSigners(List<String> signers) {
        this.signers = signers;
    }

    @Exclude
    public String getPetitionKey() {
        return petitionKey;
    }

    @Exclude
    public void setPetitionKey(String petitionKey) {
        this.petitionKey = petitionKey;
    }

    public List<PetitionUpdates> getmPetitionUpdatesList() {
        return mPetitionUpdatesList;
    }

    public void setmPetitionUpdatesList(List<PetitionUpdates> mPetitionUpdatesList) {
        this.mPetitionUpdatesList = mPetitionUpdatesList;
    }



    public Petition() { }

    public Petition( String petitionName, String petitionSupporter, String petitionDescription, String petitionImage,int petitionSignatureGoal,int petitionSignature,List<PetitionUpdates>petitionUpdatesList) {
        mPetitionName =petitionName;
        mPetitionSupporter =petitionSupporter;
        mPetitionDescription =petitionDescription;
        mPetitionImageURL=petitionImage;
        mPetitionSignatureGoal=petitionSignatureGoal;
        mPetitionSignature=petitionSignature;
        mPetitionUpdatesList=petitionUpdatesList;
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

