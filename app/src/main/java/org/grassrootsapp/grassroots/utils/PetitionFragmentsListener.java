package org.grassrootsapp.grassroots.utils;

import android.support.v4.app.Fragment;

public interface PetitionFragmentsListener {

    void moveToPetitionSecondPart(Fragment fragment);
    void moveToPetitionThirdPart(Fragment fragment);
    void moveToPetitionPreview(Fragment fragment);
    void moveToSharePetition(Fragment fragment);

}
