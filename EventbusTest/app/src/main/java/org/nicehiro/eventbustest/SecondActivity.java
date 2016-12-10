package org.nicehiro.eventbustest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.nicehiro.eventbustest.eventbus.Event0;
import org.nicehiro.eventbustest.eventbus.Event1;
import org.nicehiro.eventbustest.eventbus.Event2;
import org.nicehiro.eventbustest.eventbus.Event3;
import org.nicehiro.eventbustest.eventbus.Event4;
import org.nicehiro.eventbustest.observer.MyObservable;

/**
 * Created by hiro on 16-12-10.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.send_myobserver).setOnClickListener(this);
        findViewById(R.id.send_event0).setOnClickListener(this);
        findViewById(R.id.send_event1).setOnClickListener(this);
        findViewById(R.id.send_event2).setOnClickListener(this);
        findViewById(R.id.send_event3).setOnClickListener(this);
        findViewById(R.id.next_page_2).setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    private void sendMyObserverEvent() {
        MyObservable.getsDefault().post("我是一个自定义事件");
    }

    private void sendEvent0() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new Event0());
            }
        });
        thread.start();
    }

    private void sendEvent1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new Event1());
            }
        });
        thread.start();
    }

    private void sendEvent2() {
        EventBus.getDefault().post(new Event2());
    }

    private void sendEvent3() {
        EventBus.getDefault().post(new Event3());
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(Event4 event4) {
        makeToast("event4, ThreadMode.POSING");
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = -1)
    public void onEvent(Event0 event0) {
        makeToast("event0, ThreadMode:POSTING");
    }

    private void makeToast(final String msg) {
        final String isMain = (Looper.getMainLooper() == Looper.myLooper()) ?
                "是" : "不是";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SecondActivity.this, "SecondActivity: " + msg + "\n"
                        + isMain + "主线程", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_myobserver:
                sendMyObserverEvent();
                break;
            case R.id.send_event0:
                sendEvent0();
                break;
            case R.id.send_event1:
                sendEvent1();
                break;
            case R.id.send_event2:
                sendEvent2();
                break;
            case R.id.send_event3:
                sendEvent3();
                break;
            case R.id.next_page_2:
                Intent intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
