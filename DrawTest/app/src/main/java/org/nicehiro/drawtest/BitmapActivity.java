package org.nicehiro.drawtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by root on 16-10-26.
 */

public class BitmapActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        findViewById(R.id.bitmap_decode).setOnClickListener(this);
        findViewById(R.id.bitmap_selector).setOnClickListener(this);
        findViewById(R.id.bitmap_level_list).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bitmap_decode:
                BitmapDecoder.decodeFile(this);
                break;
        }
    }
}
