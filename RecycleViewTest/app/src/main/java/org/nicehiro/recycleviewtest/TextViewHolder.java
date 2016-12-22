package org.nicehiro.recycleviewtest;

import android.view.View;
import android.widget.TextView;

/**
 * Created by root on 16-10-14.
 */

public class TextViewHolder extends MessageViewHolder {

    private TextView text;

    public TextViewHolder(View itemView) {
        super(itemView);

        text = (TextView) itemView.findViewById(R.id.message_content);
    }

    public TextView getText() {
        return text;
    }
}
