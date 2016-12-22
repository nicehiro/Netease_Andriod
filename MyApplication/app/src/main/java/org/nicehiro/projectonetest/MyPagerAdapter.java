package org.nicehiro.projectonetest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 16-11-4.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"HOME", "TWO", "THREE", "FOUR", "FIVE"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return RecycleViewFragment.newInstance(position);
        } else {
            return ListViewFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
}
