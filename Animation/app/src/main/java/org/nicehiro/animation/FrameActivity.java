package org.nicehiro.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by root on 16-10-30.
 */

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView frame;
    private AnimationDrawable drawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        frame = (ImageView) findViewById(R.id.fram);

        findViewById(R.id.begin).setOnClickListener(this);
        findViewById(R.id.end).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.begin:
                beginFrameAnim();
                break;
            case R.id.end:
                endFrameAnim();
                break;
        }
    }

    private void endFrameAnim() {
        if (drawable != null) {
            drawable.stop();
        }
    }

    private void beginFrameAnim() {
        if (drawable == null) {
            frame.setBackgroundResource(R.drawable.play_list);
            drawable = (AnimationDrawable) frame.getBackground();
            drawable.setOneShot(false);
        }

        if (!drawable.isRunning()) {
            drawable.start();
        }
    }
}
