package org.nicehiro.listviewtest;

/**
 * Created by root on 16-10-11.
 */

public class Message {
    private int headImageResId;
    private String name;
    private String content;
    private String time;

    public Message(int headImageResId, String name, String content, String time) {
        this.content = content;
        this.headImageResId = headImageResId;
        this.name = name;
        this.time = time;
    }

    public int getHeadImageResId() {
        return headImageResId;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
