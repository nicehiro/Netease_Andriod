package org.nicehiro.threadpooltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public ExecutorService mThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);
        mThreadPool = Executors.newCachedThreadPool();
        listView.setAdapter(new ImageAdapter(this));

        ThreadPool.runSingleThreadPool();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThreadPool.shutdownNow();
    }
}
