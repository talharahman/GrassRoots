package com.example.grassroots.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public interface PetitionsFeedInterface {

    void moveToDetailsPetition(Fragment fragment);
    void moveToPetitionAnim(Fragment fragment);
    void moveToPetitionUpdatesFirstFragment(Fragment fragment);
    void moveToPetitionUpdatesSecondFragment(Fragment fragment);
}
