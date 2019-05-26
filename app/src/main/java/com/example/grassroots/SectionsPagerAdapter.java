package com.example.grassroots;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.grassroots.fragment.OfficeExpFragment;
import com.example.grassroots.fragment.OverviewFragment;
import com.example.grassroots.fragment.VotePositionFragment;
import com.example.grassroots.model.ProPublica.Members.CongressMember;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.frag_overview, R.string.frag_vote_positions, R.string.frag_office_expense};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment tabFragment = null;
        switch (position) {
            case 0:
                tabFragment = new OverviewFragment();
                break;
            case 1:
                tabFragment = new VotePositionFragment();
                break;
            case 2:
                tabFragment = new OfficeExpFragment();
                break;
        }
        return tabFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
