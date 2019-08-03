package com.example.grassroots.model.user;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.grassroots.model.petition.Petition;

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
        Log.d("GETUSERID", "getCurrentUserID: " +currentUserID);
        return currentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        Log.d("SETUSERID", "SETCurrentUserID: " +"setter is null");

        this.currentUserID = currentUserID;
        Log.d("SETUSERID", "SETCurrentUserID: " +currentUserID);
    }
}
