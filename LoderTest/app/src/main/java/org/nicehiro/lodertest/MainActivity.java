package org.nicehiro.lodertest;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    private void makeToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String newMsg = msg + " is mainThread = " + (Looper.getMainLooper() == Looper.myLooper());
                Toast.makeText(MainActivity.this, newMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void button0Click() {
        AsyncTaskLoader<Long> asyncTaskLoader = new AsyncTaskLoader<Long>(this) {
            @Override
            public Long loadInBackground() {
                long i;
                for (i=0; i<10000; i++) {}
                return i;
            }
        };
        asyncTaskLoader.forceLoad();
        asyncTaskLoader.registerListener(0, new Loader.OnLoadCompleteListener<Long>() {
            @Override
            public void onLoadComplete(Loader<Long> loader, Long data) {
                makeToast("onLoadCompleteListener: " + data);
            }
        });
    }

    private void button1Click() {
        AsyncTaskLoader<Long> asyncTaskLoader = new AsyncTaskLoader<Long>(this) {
            @Override
            public Long loadInBackground() {
                long i;
                for (i=0; i<10000; i++) {}
                return i;
            }

            @Override
            public void onCanceled(Long data) {
                makeToast("onCanceled: " + data);
            }
        };

        asyncTaskLoader.forceLoad();
        asyncTaskLoader.registerListener(0, new Loader.OnLoadCompleteListener<Long>() {
            @Override
            public void onLoadComplete(Loader<Long> loader, Long data) {
                makeToast("onLoadCompleteListener: " + data);
            }
        });

        for (int i=0; i<10000; i++) {}
        asyncTaskLoader.cancelLoad();
    }

    private void button2Click() {
        final AsyncTaskLoader<Long> asyncTaskLoader = new AsyncTaskLoader<Long>(this) {
            private boolean isCanceled = false;

            @Override
            public void cancelLoadInBackground() {
                isCanceled = true;
            }

            @Override
            public Long loadInBackground() {
                long i;
                for (i=0; i<10000 && !isCanceled; i++) {}
                return i;
            }

            @Override
            public void onCanceled(Long data) {
                makeToast("onCanceled: " + data);
            }
        };

        asyncTaskLoader.forceLoad();
        asyncTaskLoader.registerListener(0, new Loader.OnLoadCompleteListener<Long>() {
            @Override
            public void onLoadComplete(Loader<Long> loader, Long data) {
                makeToast("onLoadCompleteListener: " + data);
            }
        });

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncTaskLoader.cancelLoadInBackground();
            }
        }, 3);
    }

    private void button3Click() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                button0Click();
                break;
            case R.id.button1:
                button1Click();
                break;
            case R.id.button2:
                button2Click();
                break;
            case R.id.button3:
                button3Click();
                break;
        }
    }
}
