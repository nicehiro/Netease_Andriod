package org.nicehiro.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by hiro on 16-12-9.
 */

public class PackageRemoveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, Intent.ACTION_PACKAGE_REMOVED)) {
            Uri uri = intent.getData();

            Toast.makeText(context, "程序 " + uri.toString() + "被卸载"
                    , Toast.LENGTH_SHORT).show();
        }
    }
}
