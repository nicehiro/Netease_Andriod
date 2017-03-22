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
 * Created by hiro on 17-3-22.
 */

public class BookRecycleViewAdapterGrid extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<Book> list;

    private BookRecyclerViewAdapter.OnRecyclerViewItemClickListener listener;

    public void setOnItemClickListener(BookRecyclerViewAdapter.OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public BookRecycleViewAdapterGrid(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    private static final int TYPE_BOOK_GRID = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BOOK_GRID:
                return new BookViewHolder(inflater.inflate(R.layout.grid_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookViewHolder) {
            Book item = list.get(position);

            if (item.getImage() == null) {
                ((BookViewHolder) holder).book_image.setImageResource(R.drawable.mybook);
            } else {
                Glide.with(context)
                        .load(item.getImage())
                        .asBitmap()
                        .placeholder(R.drawable.mybook)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.mybook)
                        .centerCrop()
                        .into(((BookViewHolder) holder).book_image);
            }

            ((BookViewHolder) holder).book_title.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_BOOK_GRID;
    }

    private class BookViewHolder extends RecyclerView.ViewHolder {

        private ImageView book_image;
        private TextView book_title;

        public BookViewHolder(View itemView) {
            super(itemView);
            book_image = (ImageView) itemView.findViewById(R.id.book_image_2);
            book_title = (TextView) itemView.findViewById(R.id.book_title_2);
        }
    }
}
