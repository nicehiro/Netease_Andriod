package org.nicehiro.recycleviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by root on 16-10-14.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messageList;

    private interface ViewType {
        int TEXT = 10;
        int IMAGE = 20;
    }

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewType was from methed getItemViewType()
        if (viewType == ViewType.TEXT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_message_item, parent, false);
            return new TextViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_message_item, parent, false);
            return new ImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position) {
        if (getItemViewType(position) == ViewType.TEXT) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            TextMessage textMessage = (TextMessage) messageList.get(position);

            textViewHolder.getHead().setImageResource(textMessage.getHeadImageResId());
            textViewHolder.getTime().setText(textMessage.getTime());
            textViewHolder.getText().setText(textMessage.getText());
        } else {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            ImageMessage imageMessage = (ImageMessage) messageList.get(position);

            imageViewHolder.getHead().setImageResource(imageMessage.getHeadImageResId());
            imageViewHolder.getTime().setText(imageMessage.getTime());
            imageViewHolder.getImage().setImageResource(imageMessage.getImageResId());
        }

        if (onItemClickListern != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListern.onItemClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        if (message instanceof TextMessage) {
            return ViewType.TEXT;
        } else {
            return ViewType.IMAGE;
        }
    }

    public interface OnItemClickListern {
        void onItemClick(RecyclerView.ViewHolder viewHolder, int position);
    }

    private OnItemClickListern onItemClickListern;

    public void setOnItemClickListern(OnItemClickListern onItemClickListern) {
        this.onItemClickListern = onItemClickListern;
    }
}
