package org.nicehiro.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by hiro on 16-12-10.
 */

public class Builder0Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, "org.nicehiro.build")) {
            int code = getResultCode();
            String data = getResultData();
            Bundle bundle = getResultExtras(true);

            String msg = "建造者 0 收到通知: code " + code +
                    "; data = " + data +
                    " isOK = " + bundle.get("isOK");

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
