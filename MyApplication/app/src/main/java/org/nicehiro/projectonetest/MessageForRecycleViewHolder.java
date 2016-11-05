package org.nicehiro.projectonetest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 16-11-5.
 */

public class MessageForRecycleViewHolder extends RecyclerView.ViewHolder {

    private ImageView head;
    private TextView title;
    private TextView subTitle;
    private TextView time;

    public MessageForRecycleViewHolder(View itemView) {
        super(itemView);

        head = (ImageView) itemView.findViewById(R.id.head);
        title = (TextView) itemView.findViewById(R.id.title);
        subTitle = (TextView) itemView.findViewById(R.id.subtitle);
        time = (TextView) itemView.findViewById(R.id.time);
    }

    public ImageView getHead() {
        return head;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getSubTitle() {
        return subTitle;
    }

    public TextView getTime() {
        return time;
    }
}
