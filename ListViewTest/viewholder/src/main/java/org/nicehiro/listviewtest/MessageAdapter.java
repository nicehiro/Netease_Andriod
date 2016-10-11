package org.nicehiro.listviewtest;

import android.content.Context;
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
    private List<Message> messageList;

    private Context context;
    private LayoutInflater layoutInflater;

    public MessageAdapter(Context context, List<Message> messageList) {
        this.messageList = messageList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.text_message_item, parent, false);

            TextView time = (TextView) convertView.findViewById(R.id.message_time);
            TextView name = (TextView) convertView.findViewById(R.id.message_name);
            ImageView head = (ImageView) convertView.findViewById(R.id.message_head);
            TextView content = (TextView) convertView.findViewById(R.id.message_content);

            viewHolder = new ViewHolder(time, name, head, content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = messageList.get(position);

        viewHolder.getTime().setText(message.getTime());
        viewHolder.getName().setText(message.getName());
        viewHolder.getHead().setImageResource(message.getHeadImageResId());
        viewHolder.getContent().setText(message.getContent());

        return convertView;
    }

    private static class ViewHolder {
        private TextView time;
        private TextView name;
        private ImageView head;
        private TextView content;

        public ViewHolder(TextView time, TextView name, ImageView head, TextView content) {
            this.time = time;
            this.name = name;
            this.head = head;
            this.content = content;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getName() {
            return name;
        }

        public ImageView getHead() {
            return head;
        }

        public TextView getContent() {
            return content;
        }
    }
}
