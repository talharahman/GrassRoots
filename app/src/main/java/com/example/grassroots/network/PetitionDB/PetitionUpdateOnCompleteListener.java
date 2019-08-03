package com.example.grassroots.network.PetitionDB;

import com.example.grassroots.model.petition.PetitionUpdates;

import java.util.List;

public interface PetitionUpdateOnCompleteListener {

     void onPetitionUpdatesOnSuccess(List<PetitionUpdates> petitionUpdatesList);
     void onPetitionUpdatesOnFailure(Throwable t);
}
