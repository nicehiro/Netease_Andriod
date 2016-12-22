package org.nicehiro.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by hiro on 16-12-2.
 */
public class ThirdActivity extends AppCompatActivity {

    private int mMessageWhat;
    private Handler mHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mMessageWhat = 1;
        Message msg = Message.obtain(mHandler, mMessageWhat);
        mHandler.sendMessageAtTime(msg, SystemClock.uptimeMillis()+60000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(mMessageWhat);
    }

    private class MyHandler extends Handler {
        private WeakReference<ThirdActivity> mActivityRef;

        public MyHandler(ThirdActivity activity) {
            mActivityRef = new WeakReference<ThirdActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivityRef != null) {
                Toast.makeText(mActivityRef.get(), "消息来了", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
