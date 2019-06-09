package com.example.grassroots.network.PetitionDB;

import com.example.grassroots.model.petition.PetitionUpdates;

import java.util.List;

public interface PetitionUpdateOnCompleteListener {

     void onPetitionUpdatesOnSucces(List<PetitionUpdates> petitionUpdatesList);
     void onPetitionUpdatesOnfialer(Throwable t);
}
