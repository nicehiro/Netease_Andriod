package org.nicehiro.javathreadtest;

import android.nfc.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by hiro on 16-11-21.
 */

public class MyRestaurant {
    private static final String TAG = "MyRestaurant";
    private MainActivity.Logger logger;
    private boolean isOpen = false;

    private static int mOrderNumber = 1;
    private List<String> mOrders = new LinkedList<>();
    private List<String> mCookies = new LinkedList<>();

    private Thread mChef;
    private Thread mServer;

    private final Object mOrderLock = new Object();
    private final Object mCookiesLock = new Object();

    private final static int COOKIES_LIMIT = 10;

    public MyRestaurant(MainActivity.Logger logger) {
        this.logger = logger;
    }

    public void open() {
        if (!isOpen) {
            logger.log(TAG, "餐厅开门了");
            isOpen = true;
        }
    }

    public void close() {
        if (isOpen) {
            logger.log(TAG, "餐厅关门了");
            isOpen = false;

            if (mChef != null) {
                mChef.interrupt();
            }

            if (mServer != null) {
                mServer.interrupt();
            }
        }
    }

    private void greetGuest() {
        logger.log(TAG, "欢迎客人");
    }

    private void customerOrderAndPay() {
        logger.log(TAG, "客人点餐并付费");
        addOrder("订单 " + (mOrderNumber++));
        addOrder("订单 " + (mOrderNumber++));
    }

    private void addOrder(String order) {
        synchronized (mOrderLock) {
            mOrders.add(order);
        }
    }

    private String getOrder() {
        synchronized (mOrderLock) {
            if (!mOrders.isEmpty()) {
                return mOrders.remove(0);
            }

            return null;
        }
    }

    private void cooking() {
        logger.log(TAG, "通知厨师做餐");

        if (mChef == null) {
            Runnable cookTask = new Runnable() {
                @Override
                public void run() {
                    while (!mChef.isInterrupted()) {
                        String order = getOrder();
                        if (order != null) {
                            synchronized (mCookiesLock) {
                                while (mCookies.size() == COOKIES_LIMIT) {
                                    try {
                                        mCookiesLock.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                logger.log(TAG, "厨师做餐");

                                for (int i=0; i<10000; i++) {
                                    for (int j=0; j<10000; j++) {
                                        //TODO
                                    }
                                }

                                mCookies.add("cookies " + order);
                                mCookiesLock.notify();
                            }
                        }
                    }
                }
            };

            mChef = new Thread(cookTask);
            mChef.start();
        }
    }

    private void sendCookie() {
        logger.log(TAG, "通知服务员送餐");

        if (mServer == null) {
            final Runnable cookTask = new Runnable() {
                @Override
                public void run() {
                    while (!mServer.isInterrupted()) {
                        synchronized (mCookiesLock) {
                            while (mCookies.isEmpty()) {
                                try {
                                    mCookiesLock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (!mCookies.isEmpty()) {
                                int size = mCookies.size();
                                logger.log(TAG, "服务员送餐，手上有 " + size + " 份");
                                logger.log(TAG, "客人享用 " + mCookies.toString());
                                mCookies.clear();
                            }

                            mCookiesLock.notify();
                        }
                    }
                }
            };

            mServer = new Thread(cookTask);
            mServer.start();
        }
    }

    public void customerIn() {
        if (isOpen) {
            greetGuest();
            customerOrderAndPay();
            cooking();
            sendCookie();
        }
    }
}
