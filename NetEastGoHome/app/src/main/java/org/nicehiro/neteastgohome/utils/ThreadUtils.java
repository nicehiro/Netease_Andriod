package org.nicehiro.neteastgohome.utils;

import android.os.Looper;

import android.os.Handler;
import android.support.annotation.NonNull;

/**
 * Created by root on 17-2-8.
 */

public final class ThreadUtils {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void runOnMainThread(@NonNull Runnable runnable) {sHandler.post(runnable);}

    public static void runOnMainThreadDelay(@NonNull Runnable runnable, int delayMillis) {
        sHandler.postDelayed(runnable, delayMillis);
    }
}
