package org.nicehiro.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by root on 16-10-30.
 */

public class InterpolatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView love1;
    private ImageView love2;
    private ImageView love3;
    private ImageView love4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);

        love1 = (ImageView) findViewById(R.id.love1);
        love2 = (ImageView) findViewById(R.id.love2);
        love3 = (ImageView) findViewById(R.id.love3);
        love4 = (ImageView) findViewById(R.id.love4);

        findViewById(R.id.accelerate).setOnClickListener(this);
        findViewById(R.id.overshoot).setOnClickListener(this);
        findViewById(R.id.cycle).setOnClickListener(this);
        findViewById(R.id.anticipate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accelerate:
                accdec();
                break;
            case R.id.overshoot:
                overshoot();
                break;
            case R.id.cycle:
                cycle();
                break;
            case R.id.anticipate:
                anticipate();
                break;
        }
    }

    private void anticipate() {
        Animation animation = getTranslate();
        animation.setInterpolator(new AnticipateInterpolator(2));
        love4.startAnimation(animation);
    }

    private void cycle() {
        Animation animation = getTranslate();
        animation.setInterpolator(new CycleInterpolator(2));
        love3.startAnimation(animation);
    }

    private void overshoot() {
        Animation animation = getTranslate();
        animation.setInterpolator(new OvershootInterpolator());
        love2.startAnimation(animation);
    }

    private void accdec() {
        Animation animation = getTranslate();
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        love1.startAnimation(animation);
    }

    private Animation getTranslate() {
        Animation animation = new TranslateAnimation(0, 400, 0, 0);
        animation.setDuration(3000);
        return animation;
    }
}
