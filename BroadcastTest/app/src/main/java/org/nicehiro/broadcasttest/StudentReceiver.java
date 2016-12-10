package org.nicehiro.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;

/**
 * Created by hiro on 16-12-9.
 */

public class StudentReceiver extends BroadcastReceiver {

    private Button mButton;

    public StudentReceiver(Button button) {
        this.mButton = button;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, "org.nicehiro.student.after_school")) {
            mButton.setText("小学生回家了");
        }
    }
}
