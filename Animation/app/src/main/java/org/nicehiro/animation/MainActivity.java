package org.nicehiro.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tween).setOnClickListener(this);
        findViewById(R.id.framAnim).setOnClickListener(this);
        findViewById(R.id.object_anim).setOnClickListener(this);
        findViewById(R.id.interpolator).setOnClickListener(this);
        findViewById(R.id.layout_animator).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tween:
                startActivity(new Intent(this, TweenActivity.class));
                break;
            case R.id.framAnim:
                startActivity(new Intent(this, FrameActivity.class));
                break;
            case R.id.interpolator:
                startActivity(new Intent(this, InterpolatorActivity.class));
                break;
            case R.id.object_anim:
                startActivity(new Intent(this, ObjectAnimationActivity.class));
                break;
            case R.id.layout_animator:
                startActivity(new Intent(this, LayoutAnimatorActivity.class));
                break;
        }
    }
}
