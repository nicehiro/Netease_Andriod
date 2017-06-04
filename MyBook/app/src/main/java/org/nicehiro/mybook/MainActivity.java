package org.nicehiro.mybook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;

    private TabLayout.Tab book;
    private TabLayout.Tab search;
    private TabLayout.Tab analysz;

    private BookFragment bookFragment;
    private SearchFragment searchFragment;
    private AnalyszFragment analyszFragment;

    private static final String TAG_BOOK = "bookFragment";
    private static final String TAG_SEARCH = "searchFragment";
    private static final String TAG_ANALYSZ = "analyszFragment";

    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            bookFragment = (BookFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAG_BOOK);
        } else {
            bookFragment = new BookFragment();
        }
        searchFragment = new SearchFragment();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 1);
        }
        analyszFragment = new AnalyszFragment();
        initView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getSupportFragmentManager();
        manager.putFragment(outState, TAG_BOOK, bookFragment);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String request = bundle.getString("result");
                    Toast.makeText(MainActivity.this, request, Toast.LENGTH_LONG).show();
                    bookFragment.loadDetail(request);
                }
                break;
        }
    }

    private void scanQR() {
        Intent intent = new Intent(this, MipcaActivityCapture.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void initView() {

        tabLayout = (TabLayout) findViewById(R.id.tab_action);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] pagerTitle = {
                    MainActivity.this.getString(R.string.tab_book),
                    MainActivity.this.getString(R.string.tab_search),
                    MainActivity.this.getString(R.string.tab_analysz)};

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return bookFragment;
                    case 1:
                        return searchFragment;
                    case 2:
                        return analyszFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return pagerTitle.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pagerTitle[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);

        book = tabLayout.getTabAt(0);
        search = tabLayout.getTabAt(1);
        analysz = tabLayout.getTabAt(2);

        book.setIcon(R.drawable.mybook);
        search.setIcon(R.drawable.search);
        analysz.setIcon(R.drawable.analysz);
        book.setText(MainActivity.this.getString(R.string.tab_book));
        search.setText(MainActivity.this.getString(R.string.tab_search));
        analysz.setText(MainActivity.this.getString(R.string.tab_analysz));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    scanQR();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
