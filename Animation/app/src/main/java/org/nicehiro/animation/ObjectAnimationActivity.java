package org.nicehiro.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 16-10-30.
 */

public class ObjectAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView love;

    private TextView number;

    private static final int TRANS_X = 600;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        love = (ImageView) findViewById(R.id.love);

        number = (TextView) findViewById(R.id.number);

        findViewById(R.id.alpha).setOnClickListener(this);
        findViewById(R.id.translate).setOnClickListener(this);
        findViewById(R.id.scale).setOnClickListener(this);
        findViewById(R.id.rotate).setOnClickListener(this);
        findViewById(R.id.love_fly).setOnClickListener(this);
        findViewById(R.id.together).setOnClickListener(this);
        findViewById(R.id.timer).setOnClickListener(this);
        findViewById(R.id.calculator).setOnClickListener(this);
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
                break;
            case R.id.together:
                together();
                break;
            case R.id.timer:
                timer();
                break;
            case R.id.calculator:
                setCustomEvaluator();
                break;
        }
    }

    private void timer() {
        ValueAnimator countAnimator = ValueAnimator.ofInt(10, 0);
        countAnimator.setDuration(10000);
        countAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                number.setText("" + animation.getAnimatedValue());
            }
        });
        countAnimator.start();
    }

    private void setCustomEvaluator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        float offsetX = love.getX();
        float offsetY = love.getY();
        valueAnimator.setDuration(2000);
        valueAnimator.setObjectValues(new PointF(offsetX, offsetY), new PointF(TRANS_X + offsetX, offsetY));
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pointF = new PointF();
                float d = fraction * TRANS_X;
                pointF.x = startValue.x + d;
                pointF.y = startValue.y + (1.0f/150f) * d * d - 4 * d;
                return pointF;
            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                love.setX(pointF.x);
                love.setY(pointF.y);
            }
        });
        valueAnimator.start();
    }

    //ObjectAnimator many ways to achieve it
    private void together() {
        ObjectAnimator translate = ObjectAnimator.ofFloat(love, "translationY", -200.0f);
        translate.setDuration(3000);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(love, "rotation", 0.0f, -45.0f);
        rotate.setStartDelay(2000);
        rotate.setDuration(3000);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(love, "alpha", 1.0f, 0.0f);
        alpha.setStartDelay(2000);
        alpha.setDuration(3000);
        translate.start();
        rotate.start();
        alpha.start();

        AnimatorSet set = new AnimatorSet();
        set.play(rotate).with(translate).with(alpha);
//        set.playTogether(rotate, translate, alpha);
//        set.playSequentially(rotate, translate, alpha);

//
//        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 2.0f);
//        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(love, rotate, scaleX, scaleY);
//        animator.setDuration(1000);
//        animator.start();
//
        set.setDuration(1000);
        set.start();

//        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.love_fly);
//        animator.setTarget(love);
//        animator.start();
    }

    @Override
    public void finish() {
        super.finish();
        customExit();
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    private void customExit() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void rotate() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(love, "rotation", 0f, 360f);
        rotate.setDuration(1000);
        rotate.start();
    }

    private void scale() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(love, "scaleX", 1.0f, 2.0f);
        scaleX.setDuration(1000);
        scaleX.start();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(love, "scaleY", 1.0f, 2.0f);
        scaleY.setDuration(1000);
        scaleY.start();
        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(love, "scaleX", 2.0f, 1.0f);
        scaleX1.setDuration(1000);
        scaleX1.start();
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(love, "scaleY", 2.0f, 1.0f);
        scaleY1.setDuration(1000);
        scaleY1.start();
    }

    private void translate() {
        ObjectAnimator translate = ObjectAnimator.ofFloat(love, "translationX", 200f, -200f);
        translate.setDuration(1000);
        translate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        translate.start();
    }

    private void alpha() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(love, "alpha", 1.0f, 0.0f, 1.0f);
        alpha.setDuration(1000);
        alpha.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(ObjectAnimationActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        alpha.start();
    }
}
