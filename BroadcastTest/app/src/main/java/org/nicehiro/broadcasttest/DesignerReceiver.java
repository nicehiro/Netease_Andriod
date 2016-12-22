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

public class DesignerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, "org.nicehiro.build")) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String msg = "设计师收到通知：type = " + extras.get("type");
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putBoolean("isOK", true);
                setResult(0, "设计师处理过了", bundle);
            }
        }
    }
}
