package org.nicehiro.eventbustest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.nicehiro.eventbustest.eventbus.Event0;

/**
 * Created by hiro on 16-12-10.
 */

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViewById(R.id.send_event0_2).setOnClickListener(this);
    }

    private void sendEvent0() {
        EventBus.getDefault().post(new Event0());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_event0_2:
                sendEvent0();
                break;
        }
    }
}
