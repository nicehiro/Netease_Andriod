package org.nicehiro.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by hiro on 16-12-9.
 */

public class AnotherActivity extends AppCompatActivity {
    private static final String TAG = ">>> AnotherActivity";

    private ServiceConnection mSerCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        bindService(new Intent(this, MyService.class), mSerCon, BIND_AUTO_CREATE);

        findViewById(R.id.unbind_service_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(mSerCon);
            }
        });
    }
}
