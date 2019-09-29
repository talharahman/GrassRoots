package org.grassrootsapp.grassroots.network.PetitionDB;

import org.grassrootsapp.grassroots.model.CivicInfo.ElectedRepresentatives;

public interface SendPetitionToRepCallBack {

    void sendMyPetitionToRep(ElectedRepresentatives representative);
}
