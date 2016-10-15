package org.nicehiro.recycleviewtest;

/**
 * Created by root on 16-10-14.
 */

public class Message {
    private int headImageResId;
    private String time;

    public Message(int headImageResId, String time) {
        this.headImageResId = headImageResId;
        this.time = time;
    }

    public int getHeadImageResId() {
        return headImageResId;
    }

    public String getTime() {
        return time;
    }
}
