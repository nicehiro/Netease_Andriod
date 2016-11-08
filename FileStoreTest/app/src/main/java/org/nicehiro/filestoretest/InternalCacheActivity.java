package org.nicehiro.filestoretest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by root on 16-11-8.
 */

public class InternalCacheActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);

        findViewById(R.id.readFromCache).setOnClickListener(this);
    }

    private void writeAndReadCacheFile (Context context) {
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir.getAbsolutePath() + "/internalCache");

        try {
            FileOutputStream outputStream = new FileOutputStream(cacheFile);
            String cacheString = new String("hello internal cache");
            outputStream.write(cacheString.getBytes("utf-8"));

            outputStream.close();

            FileInputStream inputStream = new FileInputStream(cacheFile);
            String cache = Util.getStringStream(inputStream);
            Log.i("Internal Cache", cache);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readFromCache:
                writeAndReadCacheFile(this);
                break;
        }
    }
}
