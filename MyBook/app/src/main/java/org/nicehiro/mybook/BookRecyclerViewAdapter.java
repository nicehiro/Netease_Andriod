package org.nicehiro.mybook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by hiro on 17-3-17.
 */

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<Book> list;

    private OnRecyclerViewItemClickListener listener;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public BookRecyclerViewAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    private static final int TYPE_BOOK_LIST = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BOOK_LIST:
                return new BookViewHolder(inflater.inflate(R.layout.list_item, parent, false), listener);
        }
        return null;
    }

    interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookViewHolder) {
            Book item = list.get(position);

            if (item.getImage() == null) {
                ((BookViewHolder) holder).imageView.setImageResource(R.drawable.mybook);
            } else {
                Glide.with(context)
                        .load(item.getImage())
                        .asBitmap()
                        .placeholder(R.drawable.mybook)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.mybook)
                        .centerCrop()
                        .into(((BookViewHolder) holder).imageView);
            }

            ((BookViewHolder) holder).title.setText(item.getTitle());
            ((BookViewHolder) holder).author.setText("作者:" + ArrayListToString.listToString(item.getAuthor()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_BOOK_LIST;
    }

    private class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView title;
        private TextView author;
        private OnRecyclerViewItemClickListener listener;

        public BookViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            title = (TextView) itemView.findViewById(R.id.text_title);
            author = (TextView) itemView.findViewById(R.id.text_author);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}
