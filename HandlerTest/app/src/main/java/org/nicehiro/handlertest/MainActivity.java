package org.nicehiro.handlertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ExecutorService mThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.next_btn).setOnClickListener(this);
        ListView listView = (ListView) findViewById(R.id.list_view);
        if (listView != null) {
            mThreadPool = Executors.newSingleThreadExecutor();
            listView.setAdapter(new BlockAdapter(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThreadPool.shutdownNow();
    }

    public void onNextPage() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_btn:
                onNextPage();
                break;
        }
    }
}
