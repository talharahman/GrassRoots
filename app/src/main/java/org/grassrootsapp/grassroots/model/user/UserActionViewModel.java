package org.grassrootsapp.grassroots.model.user;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.grassrootsapp.grassroots.model.petition.Petition;

import java.util.List;

public class UserActionViewModel extends ViewModel {

    private MutableLiveData<List<Petition>> petitions = new MutableLiveData<>();
    private String currentUserID;
    // acts as a wrapper around object you want

    public MutableLiveData<List<Petition>> getPetitions() {
        return petitions;
    }

    public void setPetitions(List<Petition> petitions) {
        this.petitions.setValue(petitions);
    }

    public String getCurrentUserID() {
        return currentUserID;
    }

    public void setCurrentUserID(String currentUserID) {

        this.currentUserID = currentUserID;
    }
}
