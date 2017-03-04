package org.nicehiro.neteastgohome;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;
import android.util.Log;

import org.nicehiro.neteastgohome.utils.Background;
import org.nicehiro.neteastgohome.utils.ThreadUtils;

import java.util.List;

import org.nicehiro.neteastgohome.service.PollService;

/**
 * Created by root on 17-2-8.
 */

public class ExerciseApplication extends Application {
    private static ExerciseApplication instance;
    private static final int POLL_INTERVAL = 1 * 60 * 1000;

    private PollService.OnPollResultListener mListener;

    public ExerciseApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!shouldInit()) {
            return;
        }

        PollService.addListener(getPollListener());
        Background.getInstance().poll(instance);
    }

    private PollService.OnPollResultListener getPollListener() {
        if (null == mListener) {
            mListener = new PollService.OnPollResultListener() {
                @Override
                public void onPollResult(@NonNull String result) {
                    Log.i(ExerciseApplication.class.getSimpleName(), "the poll result is " + result);
                    ThreadUtils.runOnMainThreadDelay(new Runnable() {
                        @Override
                        public void run() {
                            Background.getInstance().poll(instance);
                        }
                    }, POLL_INTERVAL);
                }
            };
        }
        return mListener;
    }

    private boolean shouldInit() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int mypid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == mypid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
