package org.nicehiro.listviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 16-10-11.
 */

public class MessageAdapter extends BaseAdapter {
    private List<Object> messageList;

    private static final int ViewTypeCount = 2;
    private interface ViewType {
        int TEXT = 0;
        int IMAGE = 1;
    }

    public MessageAdapter(List<Object> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (getItemViewType(position) == ViewType.TEXT) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.text_image_item, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.message_content);

            TextMessage textMessage = (TextMessage) messageList.get(position);
            textView.setText(textMessage.getText());
        } else {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.image_message_item, parent, false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.message_image);
            ImageMessage imageMessage = (ImageMessage) messageList.get(position);
            imageView.setImageResource(imageMessage.getImageResId());
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof TextMessage) {
            return ViewType.TEXT;
        } else {
            return ViewType.IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return ViewTypeCount;
    }
}
