package org.nicehiro.neteastgohome.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.nicehiro.neteastgohome.service.PollService;

/**
 * Created by root on 17-2-8.
 */

public class Background {
    private static class InstanceHolder {
        final static Background INSTANCE = new Background();
    }

    public static Background getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void poll(@NonNull Context context) {
        Intent intent = new Intent(context, PollService.class);
        context.startService(intent);
    }

    private Background() {}
}
