package org.grassrootsapp.grassroots.fragment.petition;

import android.support.v4.app.Fragment;

public interface PetitionFragmentsListener {
    void moveToPetitionFirstPart(Fragment fragment);
    void moveToPetitionSecondPart(Fragment fragment);
    void moveToPetitionThirdPart(Fragment fragment);
    void moveToPetitionPreview(Fragment fragment);
    void moveToSharePetition(Fragment fragment);
//    void moveToListOfPetitions(Fragment fragment);
//    void moveToDetailsPetition(Fragment fragment);
//    void moveToPetitionUpdatesFirstFragment(Fragment fragment);
//    void moveToPetitionUpdatesSecondFragment(Fragment fragment);

}
