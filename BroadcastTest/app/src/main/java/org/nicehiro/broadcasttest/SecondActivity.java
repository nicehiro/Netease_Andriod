package org.nicehiro.broadcasttest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hiro on 16-12-9.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private LocalBroadcastManager mLocalBroadcastManager;
    private LocalReceiver mLocalReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.after_school).setOnClickListener(this);
        findViewById(R.id.next_page2).setOnClickListener(this);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalReceiver = new LocalReceiver();
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("org.nicehiro.local_broadcast");
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);
    }

    private void afteSchool() {
        Intent intent = new Intent("org.nicehiro.student.after_school");
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.after_school:
                afteSchool();
                break;
            case R.id.next_page2:
                Intent intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }
}
