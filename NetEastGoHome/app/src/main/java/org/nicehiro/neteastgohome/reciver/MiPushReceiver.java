package org.nicehiro.neteastgohome.reciver;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import org.nicehiro.neteastgohome.AppProfile;

import java.util.List;

/**
 * Created by root on 17-2-8.
 */

public class MiPushReceiver extends PushMessageReceiver {
    private static final String TAG = "MiPushMessageReceiver";

    private String mRegId;

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        Log.i(TAG, "message received from mi push: " + miPushMessage.getContent());
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0)) ? arguments.get(0) : null;

        if (MiPushClient.COMMAND_REGISTER.equals(cmdArg1)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
                AppProfile.setsXiaoMiRegId(mRegId);
            } else {
                Log.w(TAG, "register mi push failed, code is " + miPushCommandMessage.getResultCode());
            }
        }
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onReceiveRegisterResult(context, miPushCommandMessage);
    }
}
