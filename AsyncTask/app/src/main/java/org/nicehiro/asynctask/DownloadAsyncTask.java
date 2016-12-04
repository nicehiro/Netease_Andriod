package org.nicehiro.asynctask;

import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hiro on 16-12-2.
 */

public class DownloadAsyncTask extends AsyncTask<String, Float, Float> {

    private String mUrl;
    private WeakReference<Button> mButtonRef;
    private String mDownloadPath;
    private float mDownloadSize;

    public DownloadAsyncTask(String url, String fileName, Button mButton) {
        this.mUrl = url;
        this.mButtonRef = new WeakReference<Button>(mButton);
        this.mDownloadPath = Environment.getExternalStorageDirectory() +
                File.separator +
                "Download" +
                File.separator +
                fileName;
    }

    @Override
    protected void onPreExecute() {
        // UI 线程
        if (mButtonRef.get() != null) {
            mButtonRef.get().setText("开始下载");
        }
        mDownloadSize = 0;
    }

    @Override
    protected Float doInBackground(String... strings) {
        // 后台线程
        OutputStream os = null;

        try {
            // 下载需要网络权限
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            File file = new File(mDownloadPath);

            file.mkdirs();
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);

            byte[] buffer = new byte[4 * 1024];
            int count = 0;
            while ((count = is.read(buffer)) != -1 && !isCancelled()) {
                fos.write(buffer);
                mDownloadSize += 1.0f * count / 1024;
                publishProgress(mDownloadSize);
            }
            os.flush();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mDownloadSize;
    }

    @Override
    protected void onPostExecute(Float aFloat) {
        if (mButtonRef.get() != null) {
            mButtonRef.get().setText("下载完成");
        }
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        // UI 线程
        String progress = getDownloadInfo(values[0]);
        if (mButtonRef.get() != null) {
            mButtonRef.get().setText(progress);
        }
    }

    @Override
    protected void onCancelled(Float aFloat) {
        super.onCancelled(aFloat);
    }

    private String getDownloadInfo(float mDownloadSize) {
        return (mDownloadSize < 1024) ?
                Float.toString(mDownloadSize) + " K" :
                Float.toString(mDownloadSize / 1024) + "M";
    }

}
