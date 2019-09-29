package org.grassrootsapp.grassroots.network.PetitionDB;

import org.grassrootsapp.grassroots.model.petition.PetitionUpdates;

import java.util.List;

public interface PetitionUpdateOnCompleteListener {

     void onPetitionUpdatesOnSuccess(List<PetitionUpdates> petitionUpdatesList);
     void onPetitionUpdatesOnFailure(Throwable t);
}
