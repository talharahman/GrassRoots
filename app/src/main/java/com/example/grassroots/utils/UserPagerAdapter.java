package com.example.grassroots.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grassroots.fragment.user.UserActions;
import com.example.grassroots.fragment.user.UserFeed;

public class UserPagerAdapter extends FragmentPagerAdapter {

    public UserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UserActions();
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
