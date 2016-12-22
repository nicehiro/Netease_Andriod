package org.nicehiro.viewpagertest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 16-10-17.
 */

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = (ViewPager) findViewById(R.id.pager);

        setupViewPager();
    }

    private void setupViewPager() {
        viewPager.setAdapter(new ColorPagerAdapter(new ColorData(), this));
    }
}
