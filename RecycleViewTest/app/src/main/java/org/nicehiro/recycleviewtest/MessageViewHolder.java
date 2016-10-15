package org.nicehiro.recycleviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 16-10-14.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private ImageView head;
    private TextView time;

    public MessageViewHolder(View itemView) {
        super(itemView);

        head = (ImageView) itemView.findViewById(R.id.message_head);
        time = (TextView) itemView.findViewById(R.id.message_time);
    }

    public ImageView getHead() {
        return head;
    }

    public TextView getTime() {
        return time;
    }
}
