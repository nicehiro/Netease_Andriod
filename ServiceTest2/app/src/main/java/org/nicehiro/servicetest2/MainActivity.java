package org.nicehiro.servicetest2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.nicehiro.servicetest2.aidl.Callback;
import org.nicehiro.servicetest2.aidl.ParcelableObject;
import org.nicehiro.servicetest2.aidl.TestServiceCall;
import org.nicehiro.servicetest2.aidl.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ServiceConnection mSerConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            final TestServiceCall serviceCall = TestServiceCall.Stub.asInterface(iBinder);
            try {
                serviceCall.setCallback(new Callback.Stub() {
                    @Override
                    public void callback(int what) throws RemoteException {
                        Log.d(TAG, ">>> get service callback" + what);
                    }
                });

                Log.d(TAG, ">>> add: " + serviceCall.add(1, 2));
                byte[] arg1 = new byte[]{1};
                byte[] arg2 = new byte[1];
                byte[] arg3 = new byte[]{22};
                serviceCall.testOut(arg1, arg2, arg3, new ParcelableObject(2, "test", new User("username")));
                Log.d(TAG, ">>> testOut:" + arg1[0] + "," + arg2[0]);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService(new Intent(this, TestService.class), mSerConn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mSerConn);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_BACKGROUND:
                break;
            case TRIM_MEMORY_COMPLETE:
                break;
            case TRIM_MEMORY_MODERATE:
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                break;
            case TRIM_MEMORY_RUNNING_MODERATE:
                break;
            case TRIM_MEMORY_UI_HIDDEN:
                break;
        }
    }
}
