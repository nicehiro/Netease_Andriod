package org.nicehiro.eventbustest;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.nicehiro.eventbustest.observer.IObserver;
import org.nicehiro.eventbustest.observer.MyObservable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.next_page).setOnClickListener(this);
        findViewById(R.id.send_event4).setOnClickListener(this);
        findViewById(R.id.remove_stricky_event).setOnClickListener(this);

        MyObservable.getsDefault().register(this);
        EventBus.getDefault().register(this);
    }

    private void send_event4() {
        EventBus.getDefault().postSticky(new Event4());
    }

    private void removeStickyEvent() {
        EventBus.getDefault().removeStickyEvent(Event4.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_page:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.send_event4:
                send_event4();
                break;
            case R.id.remove_stricky_event:
                removeStickyEvent();
                break;
        }
    }

    @Override
    public void onEventReceive(Object... params) {
        String msg = "params = ";
        if (params != null) {
            for (Object param : params) {
                msg += param + "; ";
            }
        } else {
            msg += "null";
        }

        makeToast(msg);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(Event0 event0) {
        makeToast("event0, ThreadMode.POSTING");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event1 event1) {
        makeToast("event1, ThreadMode.MAIN");
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Event2 event2) {
        makeToast("event2, ThreadMode:BACKGROUND");
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(Event3 event3) {
        makeToast("event3, ThreadMode:ASYNC");
    }

    private void makeToast(final String msg) {
        final String isMain = (Looper.getMainLooper() == Looper.myLooper()) ?
                "是" : "不是";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "MainActivity: " + msg + "\n"
                        + isMain + "主线程", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyObservable.getsDefault().unregister(this);
        EventBus.getDefault().unregister(this);
    }
}
