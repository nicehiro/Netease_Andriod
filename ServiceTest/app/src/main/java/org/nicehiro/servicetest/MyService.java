package org.nicehiro.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = ">>>TestService";

    public static final class INTENT_EXTRA {
        public static String A = "a";
    }

    private Callback mCallback;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Handler clientHandler = (Handler) msg.obj;
            Log.d(TAG, "receive message: " + msg.what + ", " + msg.obj);
            clientHandler.sendEmptyMessage(2);
        }
    };

    @Override
    public void onCreate() {
        Log.d(TAG, "service onCreate, looper:" + Looper.myLooper());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "service onStartCommand, Looper:" + Looper.myLooper() + "," + intent);
        if (intent != null) {
            Log.d(TAG, "service arg: " + intent.getIntExtra(INTENT_EXTRA.A, 0));
        }
        if (mCallback != null) {
            mCallback.sendMessageToActivity(88, 0, 0, null);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "service onDestroy");
        super.onDestroy();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "service onBind");
        return new Messenger(mHandler).getBinder();
//        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        boolean ret = super.onUnbind(intent);
        Log.d(TAG, "service onUnbind, return " + ret);
        return ret;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "service onRebind");
        super.onRebind(intent);
    }

    public class MyBinder extends Binder {
        public int count(int a, int b) {
            return a + b;
        }

        public void setCallback(Callback Callback) {
            mCallback = Callback;
        }
    }

    interface Callback {
        void sendMessageToActivity(int what, int arg1, int arg2, Object object);
    }
}
