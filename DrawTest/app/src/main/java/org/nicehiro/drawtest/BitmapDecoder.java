package org.nicehiro.drawtest;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by root on 16-10-26.
 */

public class BitmapDecoder {

    public static Bitmap decodeFile(Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 3427 / 300;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        String path = Environment.getExternalStorageDirectory().getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(path + "/tiger.jpg", options);
        log(context, "bitmap in memory: " + bitmap.getByteCount());

        return bitmap;
    }

    private static void log(Context context, String message) {
        Toast.makeText(context, "message", Toast.LENGTH_SHORT).show();
        Log.d("Bitmap", message);
    }

    public static Bitmap decodeStream(String path) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(path);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Bitmap decodeResource(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lenna);
        log(context, "bitmap in memory: " + bitmap.getByteCount());
        return bitmap;
    }

    public static Bitmap decodeAssets(Context context) {
        AssetFileDescriptor afd = null;

        try {
            afd = context.getAssets().openFd("lenna_top.jpg");
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor());
            log(context, "bitmap in memory: " + bitmap.getByteCount());
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (afd != null) {
                try {
                    afd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void saveBitmap(Bitmap bitmap, OutputStream os) {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
    }

}
