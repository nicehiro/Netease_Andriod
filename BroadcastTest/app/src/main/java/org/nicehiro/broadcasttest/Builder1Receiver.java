package org.nicehiro.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by hiro on 16-12-10.
 */

public class Builder1Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, "org.nicehiro.build")) {
            String msg = "建造者 1 收到通知";
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
