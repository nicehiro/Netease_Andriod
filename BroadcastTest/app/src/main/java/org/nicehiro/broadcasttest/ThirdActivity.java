package org.nicehiro.broadcasttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hiro on 16-12-10.
 */

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViewById(R.id.local_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("org.nicehiro.local_broadcast");
        lbm.sendBroadcast(intent);
    }
}
