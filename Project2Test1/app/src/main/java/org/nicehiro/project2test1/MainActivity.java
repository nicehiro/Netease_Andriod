package org.nicehiro.project2test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService mThreadPool;

    private final static String TAG = "FIXEDTHREADPOOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fixedThreadPoolDemo();

    }

    private void fixedThreadPoolDemo() {
        mThreadPool = Executors.newFixedThreadPool(5);
        for (int i=0; i<10; i++) {
            final int index = i;

            mThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, Thread.currentThread().getName() + ", index: " +
                    index);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
