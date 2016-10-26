package org.nicehiro.drawtest;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by root on 16-10-26.
 */

public class MatrixActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        imageView = (ImageView) findViewById(R.id.image_view_tiger);

        findViewById(R.id.full_with).setOnClickListener(this);
        findViewById(R.id.rotate).setOnClickListener(this);
        findViewById(R.id.concat).setOnClickListener(this);
        findViewById(R.id.concat2).setOnClickListener(this);
        findViewById(R.id.brief_concat).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.full_with:
                fullWith();
                break;
            case R.id.rotate:
                rotate();
                break;
            case R.id.concat:
                concat();
                break;
            case R.id.concat2:
                concatTranslate();
                break;
            case R.id.brief_concat:
                briefConcat();
        }
    }

    private void briefConcat() {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        int viewWidth = imageView.getWidth();
        int viewHeight = imageView.getHeight();

        float ratio = scale(imageView);
        Matrix matrix = new Matrix();
        matrix.setRotate(45, viewWidth/2, viewHeight/2);
        matrix.postScale(ratio, ratio);
        matrix.postTranslate(100, -100);

        imageView.setImageMatrix(matrix);
    }

    private void concat() {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        int viewWidth = imageView.getWidth();
        int viewHeight = imageView.getHeight();

        Matrix translate = new Matrix();
        float scale = scale(imageView);
        translate.setScale(scale, scale);

        Matrix rotate = new Matrix();
        rotate.setRotate(45, viewWidth/2, viewHeight/2);

        rotate.postConcat(translate);
        imageView.setImageMatrix(rotate);
    }

    private void concatTranslate() {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        int viewWidth = imageView.getWidth();
        int viewHeight = imageView.getHeight();

        Matrix scale = new Matrix();
        float ratio = scale(imageView);
        scale.setScale(ratio, ratio);

        Matrix rotate = new Matrix();
        rotate.setRotate(45, viewWidth/2, viewHeight/2);
        rotate.postConcat(scale);

        Matrix translate = new Matrix();
        translate.setTranslate(100, -100);
        rotate.postConcat(translate);

        imageView.setImageMatrix(rotate);
    }

    private void rotate() {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        int viewWidth = imageView.getWidth();
        int viewHeight = imageView.getHeight();

        Matrix matrix = new Matrix();
        matrix.setRotate(45, viewWidth/2, viewHeight/2);
        imageView.setImageMatrix(matrix);
    }

    private void fullWith() {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix matrix = new Matrix();
        float scale = scale(imageView);
        matrix.setScale(scale, scale);
        imageView.setImageMatrix(matrix);
    }

    private float scale(ImageView imageView) {
        int viewWidth = imageView.getMeasuredWidth();
        int viewHeight = imageView.getMeasuredHeight();

        int imageWidth = imageView.getDrawable().getIntrinsicWidth();
        int imageHeight = imageView.getDrawable().getIntrinsicHeight();

        float scaleX = (float) viewWidth / imageWidth;
        float scaleY = (float) viewHeight / imageHeight;
        return Math.max(scaleX, scaleY);
    }
}
