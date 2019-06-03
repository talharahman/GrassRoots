package com.example.grassroots.fragment.petition;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.grassroots.model.petition.Petition;

public interface PetitionFragmentsListener {
    void moveToPetitionFirstPart(Fragment fragment);
    void moveToPetitionSecondPart(Fragment fragment);
    void moveToPetitionThirdPart(Fragment fragment);
    void moveToPetitionPreview(Fragment fragment);
    void moveToSharePetition(Fragment fragment);
//    void moveToListOfPetitions(Fragment fragment);
//    void moveToDetailsPetition(Fragment fragment);
//    void moveToPetitionUpdatesFirstFrgament(Fragment fragment);
//    void moveToPetitionUpdatesSecondFragment(Fragment fragment);

}
