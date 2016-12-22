package org.nicehiro.drawtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by root on 16-10-27.
 */

public class CanvasActivity extends AppCompatActivity implements View.OnClickListener {

    private Bitmap bitmap;
    private Canvas canvas;

    private ImageView imageView;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    private static final int PADDING = 100;

    private static final float[] values = {2.0f, 3.0f, 4.5f, 7.5f};
    private static final String[] items = {"season 1", "season 2", "season 3", "season 4"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        prepareCanvas();

        imageView = (ImageView) findViewById(R.id.canvas_draw);

        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.draw_line).setOnClickListener(this);
        findViewById(R.id.draw_line).setOnClickListener(this);
        findViewById(R.id.draw_text).setOnClickListener(this);
        findViewById(R.id.draw_rect).setOnClickListener(this);
        findViewById(R.id.draw_name).setOnClickListener(this);
        findViewById(R.id.draw_path).setOnClickListener(this);
        findViewById(R.id.custom_draw).setOnClickListener(this);
    }

    private void prepareCanvas() {
        bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                clear();
                break;
            case R.id.draw_line:
                drawLine();
                break;
            case R.id.draw_text:
                drawText();
                break;
            case R.id.draw_rect:
                translate();
                break;
            case R.id.draw_name:
                scale();
                break;
            case R.id.draw_path:
                drawPath();
                break;
            case R.id.custom_draw:
                onCustomDraw();
                break;
        }
    }

    private void onCustomDraw() {
        MsgImageView imageView = (MsgImageView) findViewById(R.id.custom_on_draw);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.histogram);
    }

    private void drawPath() {
        float[] growths = new float[3];
        for (int i=0; i<growths.length; i++) {
            growths[i] = values[i+1] - values[i];
        }

        int start = PADDING + 150;
        Path path = new Path();
        path.moveTo(start, HEIGHT-PADDING-values[0]*100);

        for (int i=1; i<=growths.length; ++i) {
            float x = start + 150*i;
            float y = HEIGHT - PADDING - growths[i-1] * 100;
            path.lineTo(x, y);
        }

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        saveBitmap();
    }

    private void scale() {
        canvas.save();

        Drawable dog = getResources().getDrawable(R.drawable.dog);
        dog.setBounds(0, 0, WIDTH, HEIGHT);

        canvas.translate(WIDTH*0.88f, HEIGHT*0.6f);
        canvas.scale(1.0f/10, 1.0f/10);
        canvas.rotate(270, WIDTH/2, HEIGHT/2);

        Path path = new Path();
        Path.Direction direction = Path.Direction.CW;
        path.addCircle(WIDTH/2, HEIGHT/2, HEIGHT/2, direction);
        canvas.clipPath(path);

        dog.draw(canvas);

        canvas.restore();

        canvas.save();
        canvas.translate(WIDTH*0.9f, HEIGHT*0.6f-20);
        canvas.rotate(270, 0, 0);

        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.BLACK);
        paint.setFakeBoldText(true);
        canvas.drawText("Hiro's work", 0, 40, paint);
        canvas.restore();

        saveBitmap();
    }

    private void translate() {
        for (int i = 0; i < 4; i++) {
            canvas.save();
            canvas.translate(PADDING + (i + 1) * 150, 0);
            drawRect(values[i], items[i]);
            canvas.restore();
        }
    }

    private void drawRect(float value, String item) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);

        float left = -15;
        float top = (int) (HEIGHT - PADDING - value * 100);
        float right = 15;
        float bottom = HEIGHT - PADDING;
        canvas.drawRect(left, top, right, bottom, paint);

        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.CYAN);
        String text = String.valueOf(value);
        canvas.drawText(text, 0, top-10, paint);

        canvas.save();
        canvas.rotate(-30, 30, bottom+30);
        paint.setColor(Color.BLACK);
        canvas.drawText(item, 0, bottom+30, paint);
        canvas.restore();

        saveBitmap();
    }

    private void drawText() {
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextAlign(Paint.Align.CENTER);

        String title = "Histogram Sample";
        canvas.drawText(title, WIDTH/2, 70, paint);

        paint.setColor(0xFFFF4081);
        paint.setStrokeWidth(6);
        float width = paint.measureText(title);
        float startX = (WIDTH-width)/2;
        float startY = 70 + 15;
        canvas.drawLine(startX, startY, startX+width, startY, paint);

        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.RIGHT);
        for (int i=0; i<9; i++) {
            float x = PADDING - 20;
            float y = HEIGHT - (i+1) * 100;
            canvas.drawText(String.valueOf(i), x, y, paint);
        }

        saveBitmap();
    }

    private void drawLine() {
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        int originX = PADDING;
        int originY = HEIGHT - PADDING;

        canvas.drawLine(originX, originY, WIDTH-PADDING, originY, paint);
        canvas.drawLine(originX, originY, originX, PADDING, paint);

        saveBitmap();
    }

    private void clear() {
        canvas.drawColor(Color.WHITE);
        imageView.setImageBitmap(bitmap);
    }

    private void saveBitmap() {
        imageView.setImageBitmap(bitmap);

        String path = Environment.getExternalStorageDirectory().getPath() + "/canvas.png";
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
}
