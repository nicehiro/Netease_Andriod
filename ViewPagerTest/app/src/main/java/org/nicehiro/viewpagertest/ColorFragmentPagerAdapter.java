package org.nicehiro.viewpagertest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 16-10-17.
 */

public class ColorFragmentPagerAdapter extends FragmentPagerAdapter {
    private final IColorData colorData;

    public ColorFragmentPagerAdapter(FragmentManager fm, IColorData colorData) {
        super(fm);

        this.colorData = colorData;
    }

    @Override
    public Fragment getItem(int position) {
        ColorFragment fragment = new ColorFragment();
        setupPage(fragment, position);
        return fragment;
    }

    private void setupPage(ColorFragment fragment, int position) {
        Bundle args = new Bundle();
        args.putString("colorName", colorData.getColorName(position));
        args.putInt("colorValue", colorData.getColorValue(position));
        fragment.setArguments(args);
    }

    @Override
    public int getCount() {
        return colorData.getCount();
    }
}
