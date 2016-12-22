package org.nicehiro.eventbustest.observer;

/**
 * Created by hiro on 16-12-10.
 */

public interface IObserver {
    // 订阅者/观察者
    void onEventReceive(Object... params);
}
