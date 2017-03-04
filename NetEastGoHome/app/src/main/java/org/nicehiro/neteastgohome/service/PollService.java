package org.nicehiro.neteastgohome.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.nicehiro.neteastgohome.utils.ThreadUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 17-2-8.
 */

public class PollService extends IntentService {

    public interface OnPollResultListener {
        void onPollResult(@NonNull String result);
    }

    private static Set<OnPollResultListener> sListener = new HashSet<>();

    public static void addListener(@NonNull final OnPollResultListener listener) {
        if (!ThreadUtils.isMainThread()) {
            throw new RuntimeException("this method can only be called from main thread.");
        }
        sListener.add(listener);
    }

    public static void removeListener(@NonNull final OnPollResultListener listener) {
        if (!ThreadUtils.isMainThread()) {
            throw new RuntimeException("this method can only be called from main thread.");
        }
        sListener.remove(listener);
    }

    public PollService() {
        super("PollService");
    }

    private void notifyResult(final String result) {
        ThreadUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                Set<OnPollResultListener> listeners = new HashSet<>();
                listeners.addAll(sListener);
                for (OnPollResultListener listener : listeners) {
                    listener.onPollResult(result);
                }
                listeners.clear();
            }
        });
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO check data from org.nicehiro.neteastgohome.service
        notifyResult("fake news");
    }
}
