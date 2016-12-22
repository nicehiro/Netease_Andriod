package org.nicehiro.drawtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.src_scale).setOnClickListener(this);
        findViewById(R.id.matrix).setOnClickListener(this);
        findViewById(R.id.tint).setOnClickListener(this);
        findViewById(R.id.bitmap).setOnClickListener(this);
        findViewById(R.id.canvas).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.src_scale:
                startActivity(new Intent(this, SrcAndScaleTypeActivity.class));
                break;
            case R.id.matrix:
                startActivity(new Intent(this, MatrixActivity.class));
                break;
            case R.id.tint:
                startActivity(new Intent(this, TintActivity.class));
                break;
            case R.id.bitmap:
                startActivity(new Intent(this, BitmapActivity.class));
                break;
            case R.id.canvas:
                startActivity(new Intent(this, CanvasActivity.class));
                break;
        }
    }
}
