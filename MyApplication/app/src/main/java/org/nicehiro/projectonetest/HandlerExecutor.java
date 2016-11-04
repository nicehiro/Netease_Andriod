package org.nicehiro.projectonetest;

import android.os.Handler;

public class HandlerExecutor implements PlayExecutor {
    private Runnable runner;

    private final Handler handler;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            runner.run();
        }
    };

    public HandlerExecutor() {
        this(null);
    }

    public HandlerExecutor(Handler handler) {
        this.handler = handler != null ? handler : new Handler();
    }

    @Override
    public void setRunner(Runnable runner) {
        this.runner = runner;
    }

    @Override
    public void requestNow() {
        handler.post(runnable);
    }

    @Override
    public void requestDelayed(int interval) {
        handler.postDelayed(runnable, interval);
    }

    @Override
    public void drain() {
        handler.removeCallbacks(runnable);
    }
}
