package com.emre.androbooster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ogrenci on 22.12.2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FirstScreen tabf = new FirstScreen();
                return tabf;
            case 1:
                AppBooster tab0 = new AppBooster();
                return tab0;
            case 2:
                BoostingModesFragments tab1 = new BoostingModesFragments();
                return tab1;
            case 3:
                RAMManagementFragment tab2 = new RAMManagementFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}