package org.nicehiro.animation;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by root on 16-10-30.
 */

public class LayoutAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layout;

    private int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animator);

        layout = (LinearLayout) findViewById(R.id.root);
        findViewById(R.id.add_view).setOnClickListener(this);
        layout.setLayoutTransition(getTransition());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_view:
                addView();
        }
    }

    private void addView() {
        final Button button = new Button(this);
        button.setText("Button" + i++);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(button);
                i --;
            }
        });

        layout.addView(button);
    }

    private LayoutTransition getTransition() {
        LayoutTransition transition = new LayoutTransition();
        transition.setAnimator(LayoutTransition.APPEARING, transition.getAnimator(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, transition.getAnimator(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGING, transition.getAnimator(LayoutTransition.CHANGING));

        return transition;
    }
}
