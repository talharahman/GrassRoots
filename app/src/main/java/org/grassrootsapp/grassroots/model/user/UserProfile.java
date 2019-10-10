package org.grassrootsapp.grassroots.model.user;

import org.grassrootsapp.grassroots.model.petition.Petition;

import java.util.List;

public class UserProfile {

    private long userId;
    // ownerId = userId
    private List<Petition> myPetitions;
    private List<Petition> signedPetitions;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Petition> getMyPetitions() {
        return myPetitions;
    }

}