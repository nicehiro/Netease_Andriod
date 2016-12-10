package org.nicehiro.eventbustest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiro on 16-12-10.
 */

public class MyObservable {
    // 发送事件

    private List<IObserver> mObserver = new ArrayList<>();
    private static MyObservable sDefault = null;

    public static MyObservable getsDefault() {
        if (sDefault == null) {
            synchronized (MyObservable.class) {
                if (sDefault == null) {
                    sDefault = new MyObservable();
                }
            }
        }

        return sDefault;
    }

    public void register(IObserver observer) {
        if (observer != null) {
            mObserver.add(observer);
        }
    }

    public void unregister(IObserver observer) {
        if (observer != null) {
            mObserver.remove(observer);
        }
    }

    public void post(Object... params) {
        for (IObserver observer : mObserver) {
            observer.onEventReceive(params);
        }
    }
}
