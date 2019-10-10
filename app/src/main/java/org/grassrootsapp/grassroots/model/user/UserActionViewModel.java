package org.grassrootsapp.grassroots.model.user;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.grassrootsapp.grassroots.model.petition.Petition;

import java.util.List;

public class UserActionViewModel extends ViewModel {

    private MutableLiveData<List<Petition>> petitionsSignedByUser = new MutableLiveData<>();
    private MutableLiveData<List<Petition>> petitionsSignedForUser = new MutableLiveData<>();
    private String currentUserID;

    public void setPetitionsSignedByUser(MutableLiveData<List<Petition>> petitionsSignedByUser) {
        this.petitionsSignedByUser = petitionsSignedByUser;
    }

    public MutableLiveData<List<Petition>> getPetitionsSignedForUser() {
        return petitionsSignedForUser;
    }

    public void setPetitionsSignedForUser(MutableLiveData<List<Petition>> petitionsSignedForUser) {
        this.petitionsSignedForUser = petitionsSignedForUser;
    }


    public MutableLiveData<List<Petition>> getPetitionsSignedByUser() {
        return petitionsSignedByUser;
    }

    public void setUserHistoryPetitions(List<Petition> userHistoryPetitions) {
        this.petitionsSignedByUser.setValue(userHistoryPetitions);
    }

    public String getCurrentUserID() {
        return currentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }
}
