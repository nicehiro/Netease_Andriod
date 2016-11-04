package org.nicehiro.projectonetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by root on 16-11-4.
 */

public class ViewTestThree extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerSlidingTabStrip tabStrip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_3);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);

        tabStrip.setViewPager(viewPager);
    }
}
