package org.nicehiro.projectonetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;


/**
 * Created by root on 16-11-5.
 */

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        TextView tvTitle = (TextView) findViewById(R.id.demo_title);
        TextView tvSubTitle = (TextView) findViewById(R.id.demo_subtitle);

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra("message");

        if (message != null) {
            tvTitle.setText(message[0]);
            tvSubTitle.setText(message[1]);
        }
    }
}
