package org.nicehiro.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    private void makeToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "msg", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void button0Click() {
        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask("http://study.163.com/pub/study-android-official.apk",
                "study.apk", button0);
        downloadAsyncTask.execute();
    }

    private void button1Click() {
        String url = "http://study.163.com/pub/study-android-official.apk";

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(url, "study0.apk", button0);
        downloadAsyncTask.execute();

        DownloadAsyncTask downloadAsyncTask1 = new DownloadAsyncTask(url, "study1.apk", button1);
        downloadAsyncTask1.execute();
    }

    private void button2Click() {
        String url = "http://study.163.com/pub/study-android-official.apk";

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(url, "study0.apk", button0);
        downloadAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        DownloadAsyncTask downloadAsyncTask1 = new DownloadAsyncTask(url, "study1.apk", button1);
        downloadAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void button3Click() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.makeToast("AsyncTask 执行 runnable");
            }
        });
    }

    private void button4Click() {
        String url = "http://study.163.com/pub/study-android-official.apk";

        if (button4.getTag() == null) {
            DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(url, "study.apk", button4);
            button4.setTag(downloadAsyncTask);
            downloadAsyncTask.execute();
        } else {
            DownloadAsyncTask downloadAsyncTask = (DownloadAsyncTask) button4.getTag();
            downloadAsyncTask.cancel(false);
            button4.setTag(null);
        }
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
            case R.id.button4:
                button4Click();
                break;
        }
    }
}
