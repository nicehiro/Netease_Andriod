package org.nicehiro.bitmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap;
    private Canvas canvas;

    private static final int WIDTH = 994;
    private static final int HEIGHT = 662;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareCanvas();
        imageView = (ImageView) findViewById(R.id.canvas_draw);

        draw();
    }

    private void draw() {
        canvas.drawColor(Color.WHITE);
        imageView.setImageBitmap(bitmap);

        drawLine();

        drawMap();
    }

    private void drawMap() {
        Paint paint = new Paint();
        MyColor mapColor;

        for (int i=0; i<WIDTH; i+=83) {
            for (int j=0; j<HEIGHT; j+=83) {
                mapColor = getRandColor();
                paint.setColor(mapColor.getRandColor());
                canvas.drawRect(i, j, i+81, j+81, paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(20);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(mapColor.getAlpha(), i+20, j+20, paint);
                canvas.drawText(mapColor.getRed(), i+60, j+20, paint);
                canvas.drawText(mapColor.getGreen(), i+20, j+60, paint);
                canvas.drawText(mapColor.getBlue(), i+60, j+60, paint);
            }
        }

        saveBitmap();
    }

    private void drawLine() {
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);

        for (int i=81; i<WIDTH; i+=83) {
            canvas.drawLine(i, 0, i, HEIGHT, paint);
        }
        for (int i = 81; i < HEIGHT; i += 83) {
            canvas.drawLine(0, i, WIDTH, i, paint);
        }

        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);

        for (int i=41; i<WIDTH; i+=83) {
            canvas.drawLine(i, 0, i, HEIGHT, paint);
        }
        for (int i=41; i<HEIGHT; i+= 83) {
            canvas.drawLine(0, i, WIDTH, i, paint);
        }

        saveBitmap();
    }

    private void saveBitmap() {
        imageView.setImageBitmap(bitmap);

        String path = Environment.getExternalStorageDirectory().getPath() + "/mnt/sdcard/bitmap_table.png";
        try {
            FileOutputStream fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareCanvas() {
        bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public MyColor getRandColor() {
        Random random = new Random();

        int a, r, g, b;
        a = random.nextInt(256);
        r = random.nextInt(256);
        g = random.nextInt(256);
        b = random.nextInt(256);

        MyColor myColor = new MyColor(a, r, g, b);

        return myColor;
    }

    private class MyColor {
        private int alpha;
        private int red;
        private int green;

        public String getBlue() {
            return Integer.toHexString(blue);
        }

        public String getAlpha() {
            return Integer.toHexString(alpha);
        }

        public String getRed() {
            return Integer.toHexString(red);
        }

        public String getGreen() {
            return Integer.toHexString(green);
        }

        private int blue;

        public MyColor(int alpha, int red, int green, int blue) {
            this.alpha = alpha;
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public int getRandColor() {
            int color = Color.argb(alpha, red, green, blue);

            return color;
        }
    }
}
