package org.grassrootsapp.grassroots.utils;

import android.support.v4.app.Fragment;

public interface PetitionsFeedInterface {

    void moveToDetailsPetition(Fragment fragment);
    void moveToPetitionAnim(Fragment fragment);
    void moveTosplashScreen(Fragment fragment);
    void openMainfeed();
    void moveToPetitionUpdatesFirstFragment(Fragment fragment);
    void moveToPetitionUpdatesSecondFragment(Fragment fragment);
}
