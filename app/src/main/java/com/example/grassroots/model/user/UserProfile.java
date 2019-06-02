package com.example.grassroots.model.user;

import com.example.grassroots.model.petition.Petition;

import java.util.List;

public class UserProfile {

    private int userId;
    private List<Petition> myPetitions;
    private List<Petition> signedPetitions;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Petition> getMyPetitions() {
        return myPetitions;
    }

    public void setMyPetitions(List<Petition> myPetitions) {
        this.myPetitions = myPetitions;
    }

    public List<Petition> getSignedPetitions() {
        return signedPetitions;
    }

    public void setSignedPetitions(List<Petition> signedPetitions) {
        this.signedPetitions = signedPetitions;
    }
}
