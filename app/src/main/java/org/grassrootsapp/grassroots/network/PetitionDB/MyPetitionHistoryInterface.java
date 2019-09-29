package org.grassrootsapp.grassroots.network.PetitionDB;

import org.grassrootsapp.grassroots.model.petition.Petition;

import java.util.List;

public interface MyPetitionHistoryInterface {

    void myHistoryOfPetitions(List<Petition> myPetitions);
}
