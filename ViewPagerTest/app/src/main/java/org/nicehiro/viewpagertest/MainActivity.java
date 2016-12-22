package org.nicehiro.viewpagertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.view_pager).setOnClickListener(this);
        findViewById(R.id.view_pager_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_pager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.view_pager_fragment:
                startActivity(new Intent(this, FragmentViewPagerActivity.class));
                break;
        }
    }
}
