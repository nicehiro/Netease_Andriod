package org.nicehiro.projectonetest;

import android.app.PendingIntent;
import android.preference.PreferenceActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 16-11-5.
 */

public class MessageForRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;

    private int headerCount = 1;
    private int footerCount = 1;

    private List<Message> messageList;

    public MessageForRecycleViewAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    public boolean isHeader(int position) {
        return headerCount != 0 && position < headerCount;
    }

    public boolean isFooter(int position) {
        return footerCount != 0 && position >= (footerCount+messageList.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);

            final TextView one = (TextView) view.findViewById(R.id.one);
            final TextView two = (TextView) view.findViewById(R.id.two);
            final TextView three = (TextView) view.findViewById(R.id.three);
            final TextView four = (TextView) view.findViewById(R.id.four);

            view.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paticalRefresh(view, one, two, three, four);
                }
            });
            return new HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_message, parent, false);
            return new MessageForRecycleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            return;
        } else if (holder instanceof FooterViewHolder) {
            return;
        } else if (holder instanceof MessageForRecycleViewHolder){
            Message message = messageList.get(position-headerCount);

            MessageForRecycleViewHolder messageForRecycleViewHolder = (MessageForRecycleViewHolder) holder;

            messageForRecycleViewHolder.getHead().setImageResource(message.getHeadImageResId());
            messageForRecycleViewHolder.getTitle().setText(message.getTitle());
            messageForRecycleViewHolder.getSubTitle().setText(message.getSubTitle());
            messageForRecycleViewHolder.getTime().setText(message.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size() + headerCount + footerCount;
    }

    private void paticalRefresh(View header, TextView one, TextView two, TextView three, TextView four) {
        String temp = (String) four.getText();
        four.setText(three.getText());
        three.setText(two.getText());
        two.setText(one.getText());
        one.setText(temp);

        header.refreshDrawableState();
    }
}
