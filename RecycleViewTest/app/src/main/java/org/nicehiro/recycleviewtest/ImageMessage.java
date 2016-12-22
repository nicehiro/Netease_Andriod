package org.nicehiro.recycleviewtest;

/**
 * Created by root on 16-10-14.
 */

public class ImageMessage extends Message {
    private int imageResId;

    public ImageMessage(int headImageResId, String time, int imageResId) {
        super(headImageResId, time);
        this.imageResId = imageResId;
    }

    public int getImageResId() {
        return imageResId;
    }
}
