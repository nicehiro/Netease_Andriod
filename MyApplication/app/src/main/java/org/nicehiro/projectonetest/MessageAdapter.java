package org.nicehiro.projectonetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 16-11-4.
 */

public class MessageAdapter extends BaseAdapter {

    private List<Message> messagesList;

    private Context context;
    private LayoutInflater layoutInflater;

    public MessageAdapter(List<Message> messagesList, Context context) {
        this.messagesList = messagesList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return messagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_message, parent, false);

            ImageView head = (ImageView) convertView.findViewById(R.id.head);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView subTitle = (TextView) convertView.findViewById(R.id.subtitle);
            TextView time = (TextView) convertView.findViewById(R.id.time);

            viewHolder = new ViewHolder(head, title, subTitle, time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = messagesList.get(position);

        viewHolder.getHead().setImageResource(message.getHeadImageResId());
        viewHolder.getTitle().setText(message.getTitle());
        viewHolder.getSubTitle().setText(message.getSubTitle());
        viewHolder.getTime().setText(message.getTime());

        return convertView;
    }

    private static class ViewHolder {
        private ImageView head;
        private TextView title;
        private TextView subTitle;
        private TextView time;

        public ViewHolder(ImageView head, TextView title, TextView subTitle, TextView time) {
            this.head = head;
            this.title = title;
            this.subTitle = subTitle;
            this.time = time;
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
}
