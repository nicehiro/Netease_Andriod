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

public class ExtendStorageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extendstorage);

        findViewById(R.id.extendFile).setOnClickListener(this);
        findViewById(R.id.extendCache).setOnClickListener(this);
    }

    private void writeAndReadExtendFile(Context context) {
        File extendFileDir = context.getExternalFilesDir(null);
        File extendFile = new File(extendFileDir.getAbsolutePath() + "/myextendfile.txt");
        try {
            FileOutputStream outputStream = new FileOutputStream(extendFile);
            String cacheString = new String("hello myextend file");
            outputStream.write(cacheString.getBytes());

            outputStream.close();

            FileInputStream inputStream = new FileInputStream(extendFile);
            String cache = Util.getStringStream(inputStream);
            Log.i("ExtendFile", cache);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeAndReadExtendCache(Context context) {
        File cacheDir = context.getCacheDir();
        File cacheFile = new File(cacheDir + "/myextendcache");
        try {
            FileOutputStream outputStream = new FileOutputStream(cacheFile);
            String cacheString = new String("hello my extend cache");
            outputStream.write(cacheString.getBytes("utf-8"));

            outputStream.close();

            FileInputStream inputStream = new FileInputStream(cacheFile);
            String cache = Util.getStringStream(inputStream);
            Log.i("ExtendCache", cache);
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
            case R.id.extendFile:
                writeAndReadExtendFile(this);
                break;
            case R.id.extendCache:
                writeAndReadExtendCache(this);
                break;
        }
    }
}
