package org.nicehiro.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = ">>> MainActivity";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "receive message: " + msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
        findViewById(R.id.another_activity).setOnClickListener(this);
    }

    private ServiceConnection mSerCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
//            binder.setCallback(new MyService.Callback() {
//                @Override
//                public void sendMessageToActivity(int what, int arg1, int arg2, Object object) {
//                    Log.d(TAG, "service sendMessageToActivity:" + what);
//                }
//            });
//            Log.d(TAG, "onServiceConnected count: " + binder.count(2, 3));

            Messenger myMessenger = new Messenger(iBinder);
            try {
                myMessenger.send(Message.obtain(mHandler, 1, mHandler));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisConnected");
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                Intent intent = new Intent(this, MyService.class);
                intent.putExtra(MyService.INTENT_EXTRA.A, 8);
                startService(intent);

//                intent.putExtra(MyService.INTENT_EXTRA.A, 9);
//                startService(intent);
                break;
            case R.id.stop_service:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.bind_service:
                bindService(new Intent(this, MyService.class), mSerCon, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(mSerCon);
                break;
            case R.id.another_activity:
                Intent intent1 = new Intent(this, AnotherActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
