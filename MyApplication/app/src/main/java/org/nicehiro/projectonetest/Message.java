package org.nicehiro.projectonetest;

/**
 * Created by root on 16-11-4.
 */

public class Message {

    private int headImageResId;
    private String title;
    private String subTitle;
    private String time;

    public Message(int headImageResId, String title, String subTitle, String time) {
        this.headImageResId = headImageResId;
        this.title = title;
        this.subTitle = subTitle;
        this.time = time;
    }

    public int getHeadImageResId() {
        return headImageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTime() {
        return time;
    }
}
