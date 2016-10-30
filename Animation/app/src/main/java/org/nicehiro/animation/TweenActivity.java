package org.nicehiro.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by root on 16-10-30.
 */

public class TweenActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView love;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        love = (ImageView) findViewById(R.id.love);

        findViewById(R.id.alpha).setOnClickListener(this);
        findViewById(R.id.translate).setOnClickListener(this);
        findViewById(R.id.scale).setOnClickListener(this);
        findViewById(R.id.rotate).setOnClickListener(this);
        findViewById(R.id.love_fly).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:
                alpha();
                break;
            case R.id.translate:
                translate();
                break;
            case R.id.scale:
                scale();
                break;
            case R.id.rotate:
                rotate();
                break;
            case R.id.love_fly:
                loveFly();
        }
    }

    private void loveFly() {
        Animation loveFly = AnimationUtils.loadAnimation(this, R.anim.love_fly);
        love.startAnimation(loveFly);
    }

    private void rotate() {
//        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        rotate.setDuration(3000);
        love.startAnimation(rotate);
    }

    private void scale() {
//        Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        ScaleAnimation scale = new ScaleAnimation(1, 2, 1, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(3000);
        love.startAnimation(scale);
    }

    private void translate() {
//        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        TranslateAnimation translate = new TranslateAnimation(0, 200, 0, 200);
        translate.setDuration(3000);
        love.startAnimation(translate);
    }

    private void alpha() {
//        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(3000);
        love.startAnimation(alpha);
    }
}
