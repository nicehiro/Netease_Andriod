package org.nicehiro.recycleviewtest;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by root on 16-10-14.
 */

public class ImageViewHolder extends MessageViewHolder {
    private ImageView image;

    public ImageViewHolder(View itemView) {
        super(itemView);
        this.image = (ImageView) itemView.findViewById(R.id.message_content);
    }

    public ImageView getImage() {
        return image;
    }
}
