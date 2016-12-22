package org.nicehiro.filestoretest;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by root on 16-11-8.
 */

public class ExternalStorageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externalstorage);

        findViewById(R.id.externalStorage).setOnClickListener(this);
    }

    private void writeAndReadExternalFile(Context context) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File externalDir = new File(externalStorageDirectory.getAbsolutePath() + "/mydir");

        if (!externalDir.exists()) {
            externalDir.mkdir();
        }

        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;

        File externalFile = new File(externalStorageDirectory.getAbsolutePath() + "/mydir/myexternalfile");
        try {
            outputStream = new FileOutputStream(externalFile);
            String stringCache = new String("hello myexternal file");
            outputStream.write(stringCache.getBytes());

            inputStream = new FileInputStream(externalFile);
            String file = Util.getStringStream(inputStream);
            Log.i("ExternalStorage", file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.externalStorage:
                writeAndReadExternalFile(this);
                break;
        }
    }
}
