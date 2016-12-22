package org.nicehiro.projectonetest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by root on 16-11-4.
 */

public class MyAnimation extends AppCompatActivity implements View.OnClickListener {

    private ImageView love;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        love = (ImageView) findViewById(R.id.love);

        findViewById(R.id.animation).setOnClickListener(this);
        findViewById(R.id.animator).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation:
                viewAnimation();
                break;
            case R.id.animator:
                objectAnimator();
                break;
        }
    }

    private void objectAnimator() {
        ObjectAnimator translate = ObjectAnimator.ofFloat(love, "translationX", 0, 200f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(love, "scaleX", 1f, 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(love, "scaleY", 1f, 2f);

        AnimatorSet set = new AnimatorSet();
        set.play(translate).with(scaleX).with(scaleY);
        set.setDuration(3000);
        set.start();
    }

    private void viewAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_trans_scale);
        love.startAnimation(animation);
    }
}
