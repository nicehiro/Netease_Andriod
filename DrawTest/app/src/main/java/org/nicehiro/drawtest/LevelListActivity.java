package org.nicehiro.drawtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioGroup;

/**
 * Created by root on 16-10-27.
 */

public class LevelListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);
        setup();
    }

    private void setup() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_emotion);
        final ImageView imageView = (ImageView) findViewById(R.id.img_level_list);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.argry:
                        imageView.setImageLevel(0);
                        break;
                    case R.id.smaile:
                        imageView.setImageLevel(1);
                        break;
                    case R.id.happy:
                        imageView.setImageLevel(2);
                        break;
                }
            }
        });
    }
}
