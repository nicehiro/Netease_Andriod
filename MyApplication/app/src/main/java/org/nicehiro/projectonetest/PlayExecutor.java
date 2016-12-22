package org.nicehiro.projectonetest;

public interface PlayExecutor {
    void setRunner(Runnable runner);

    void requestNow();

    void requestDelayed(int delay);

    void drain();
}
