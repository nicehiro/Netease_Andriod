package org.nicehiro.mybook;

import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

import com.google.zxing.BarcodeFormat;

import java.util.Vector;

import zxing.decoding.CaptureActivityHandler;
import zxing.view.ViewfinderView;

/**
 * Created by hiro on 17-3-20.
 */

public class ScanQRActivity extends AppCompatActivity implements Callback {
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
