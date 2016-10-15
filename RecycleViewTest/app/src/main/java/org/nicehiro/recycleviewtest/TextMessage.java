package org.nicehiro.recycleviewtest;

/**
 * Created by root on 16-10-14.
 */

public class TextMessage extends Message {
    private String text;

    public TextMessage(int headImageResId, String time, String text) {
        super(headImageResId, time);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
