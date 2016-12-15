package org.nicehiro.downloadtest1;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private long mDownloadId;
    private static final String DOWNLOAD_URL = "http://yixin.dl.126.net/update/installer/yixin.apk";
    private TextView mTvResult;

    private Receiver receiver;

    private Button mbtnDownloadself;

    private OkHttpClient client;
    private DownloadState mState = DownloadState.PENDING;
    private int mTotal;
    private static final int DELTA = 256 * 1024;
    private OutputStream outputStream;
    private int mCurrentDownload;

    private enum DownloadState {
        PENDING,
        DOWNLOADING,
        PAUSE,
        DONE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_download_by_system).setOnClickListener(this);
        mTvResult = (TextView) findViewById(R.id.tv_result);

        mbtnDownloadself = (Button) findViewById(R.id.btn_start_download);
        mbtnDownloadself.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        receiver = new Receiver();
        registerReceiver(receiver, intentFilter);

    }

    private void startDownload() {
        final DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DOWNLOAD_URL));
        request.setDestinationInExternalFilesDir(this, null, "yixin.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);

        mDownloadId = downloadManager.enqueue(request);
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (mDownloadId == reference) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(reference);

                Cursor cursor = ((DownloadManager)(getSystemService(Context.DOWNLOAD_SERVICE))).query(query);
                if (cursor.moveToFirst()) {
                    String fileName = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                    mTvResult.setText(fileName);
                }
                cursor.close();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        receiver = null;
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_download_by_system == view.getId()) {
            startDownload();
        } else if (R.id.btn_start_download == view.getId()) {
            switch (mState) {
                case PENDING:
                    startDownloadSelf();
                    mState = DownloadState.DOWNLOADING;
                    break;
                case DOWNLOADING:
                    mState = DownloadState.PAUSE;
                    break;
                case PAUSE:
                    mState = DownloadState.DOWNLOADING;
                    break;
            }
            refreshButton();
        }
    }

    private void refreshButton() {
        switch (mState) {
            case DOWNLOADING:
                mbtnDownloadself.setText(R.string.pause);
                break;
            case PENDING:
                mbtnDownloadself.setText(R.string.start_download);
                break;
            case PAUSE:
                mbtnDownloadself.setText(R.string.resume);
                break;
            case DONE:
                mbtnDownloadself.setText(R.string.done);
                break;
        }
    }

    private void startDownloadSelf() {
        getFileLength(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    mTotal = Integer.valueOf(response.header("Content-Length"));
                    outputStream = new FileOutputStream(getExternalFilesDir(null).getAbsolutePath() + "/yixin.apk");
                    downloadRange(0);
                }
            }
        });
    }

    private void downloadRange(int start) {
        Request request = new Request.Builder()
                .url(DOWNLOAD_URL)
                .addHeader("range", "bytes=" + start + "-" + Math.min(start + DELTA, mTotal)).build();

        getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && mState == DownloadState.DOWNLOADING) {
                    final byte[] bytes = response.body().bytes();
                    outputStream.write(bytes);
                    mCurrentDownload += bytes.length;
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            int progress = (int)(mCurrentDownload * 1.0 / mTotal * 100);
                            progressBar.setProgress(progress);

                            if (100 == progress) {
                                mState = DownloadState.DONE;
                                refreshButton();
                            }
                        }
                    });

                    if (mCurrentDownload >= mTotal) {
                        outputStream.flush();
                        outputStream.close();
                        return;
                    }

                    downloadRange(mCurrentDownload);
                }
            }
        });
    }

    private OkHttpClient getClient() {
        if (null == client) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).build();
        }
        return client;
    }

    private void getFileLength(Callback callback) {
        Request request = new Request.Builder()
                .url(DOWNLOAD_URL)
                .method("HEAD", null).build();

        getClient().newCall(request).enqueue(callback);
    }
}
