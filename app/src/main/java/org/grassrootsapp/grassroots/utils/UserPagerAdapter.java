package org.grassrootsapp.grassroots.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.grassrootsapp.grassroots.activity.UserActionActivity;
import org.grassrootsapp.grassroots.fragment.user.UserHistory;
import org.grassrootsapp.grassroots.fragment.user.UserFeed;

public class UserPagerAdapter extends FragmentPagerAdapter {

    public UserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UserHistory();
            case 1:
                return new UserFeed();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
