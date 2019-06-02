package com.example.grassroots.model.petition;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;

public class PetitionViewModel extends ViewModel {
    private String petitionName;
    private String petitionTarget;
    private String petitionTargetContact;

    private String petitionDescription;
    private Uri petitionImage;
    private int petitionSignature;
    private int petitionSignatureGoal;

    public String getPetitionTargetContact() {
        return petitionTargetContact;
    }

    public void setPetitionTargetContact(String petitionTargetContact) {
        this.petitionTargetContact = petitionTargetContact;
    }

    public int getPetitionSignatureGoal() {
        return petitionSignatureGoal;
    }

    public void setPetitionSignatureGoal(int petitionSignatureGoal) {
        this.petitionSignatureGoal = petitionSignatureGoal;
    }

    public void setPetitionSignature(int petitionSignature){
        this.petitionSignature = petitionSignature;
    }
    public int getPetitionSignature(){
        return petitionSignature;
    }

    public String getPetitionName() {
        return petitionName;
    }

    public void setPetitionName(String petitionName) {
        this.petitionName = petitionName;
    }

    public String getPetitionTarget() {
        return petitionTarget;
    }

    public void setPetitionTarget(String petitionTarget) {
        this.petitionTarget = petitionTarget;
    }

    public String getPetitionDescription() {
        return petitionDescription;
    }

    public void setPetitionDescription(String petitionDescription) {
        this.petitionDescription = petitionDescription;
    }

    public Uri getPetitionImage() {
        return petitionImage;
    }

    public void setPetitionImage(Uri petitionImage) {
        this.petitionImage = petitionImage;
    }
}

