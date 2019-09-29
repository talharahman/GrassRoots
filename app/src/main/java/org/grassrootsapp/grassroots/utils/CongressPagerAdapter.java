package org.grassrootsapp.grassroots.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.fragment.congress.OfficeExpFragmentv2;
import org.grassrootsapp.grassroots.fragment.congress.OverviewFragmentv2;
import org.grassrootsapp.grassroots.fragment.congress.VotePositionFragmentv2;

public class CongressPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.frag_overview, R.string.frag_vote_positions, R.string.frag_office_expense};
    private final Context mContext;

    public CongressPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment tabFragment = null;
        switch (position) {
            case 0:
                tabFragment = new OverviewFragmentv2();
                break;
            case 1:
                tabFragment = new VotePositionFragmentv2();
                break;
            case 2:
                tabFragment = new OfficeExpFragmentv2();
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
