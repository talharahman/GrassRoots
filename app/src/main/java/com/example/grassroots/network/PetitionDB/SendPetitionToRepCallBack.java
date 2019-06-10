package com.example.grassroots.network.PetitionDB;

import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;
import com.example.grassroots.model.petition.Petition;

import java.util.List;

public interface SendPetitionToRepCallBack {

    void sendMyPetitionToRep(ElectedRepresentatives representative);
}
