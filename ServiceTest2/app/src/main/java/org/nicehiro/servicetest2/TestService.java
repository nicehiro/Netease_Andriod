package org.nicehiro.servicetest2;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.nicehiro.servicetest2.aidl.Callback;
import org.nicehiro.servicetest2.aidl.ParcelableObject;
import org.nicehiro.servicetest2.aidl.TestServiceCall;

/**
 * Created by hiro on 16-12-9.
 */

public class TestService extends Service{

    private static final int NOTIFICATION_ID = 1;
    private static final String TAG = "TestService";

    private Callback mCallback;

    private TestServiceCall testServiceCall = new TestServiceCall.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public void testOut(byte[] arg1, byte[] arg2, byte[] arg3, ParcelableObject obj) throws RemoteException {
            byte read = arg1[0];
            byte read2 = arg3[0];
            arg1[0] = 8;
            arg2[0] = 9;
            Log.d(TAG, ">>> testOut: " + read + "," + read2 + "," + obj.getmA() + "," + obj.getmB() + "," + obj.getmUser().getName());
        }

        @Override
        public void setCallback(Callback callback) throws RemoteException {
            mCallback = callback;
            mCallback.callback(10);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("TestService 正在运行").setContentTitle("TestService 标题")
                .setContentText("TestService 副标题").build();
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return testServiceCall.asBinder();
    }
}
