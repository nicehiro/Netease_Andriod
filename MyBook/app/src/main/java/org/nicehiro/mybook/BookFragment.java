package org.nicehiro.mybook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hiro on 17-3-16.
 */

public class BookFragment extends Fragment{

    private static String TAG = "Book";

    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private BookRecycleViewAdapterGrid gridAdapter;

    private ArrayList<Book> list = new ArrayList<>();

    private Gson gson = new Gson();

    private RequestBook requestBook;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private ItemTouchHelper itemTouchHelper;

    private ImageView change;

    private boolean isList = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mybook, container, false);

        initView(view);

        searchDB();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {

        change = (ImageView) view.findViewById(R.id.change);

        requestBook = new RequestBook(getActivity());
        dbHelper = new DatabaseHelper(getActivity(), "History.db", null, 1);
        db = dbHelper.getWritableDatabase();

        itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(0, ItemTouchHelper.LEFT));

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isList) {
                    StaggeredGridLayoutManager gridLayoutManager = new
                            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    isList = false;
                } else {
                    recyclerView.setLayoutManager(linearLayoutManager);
                    itemTouchHelper.attachToRecyclerView(recyclerView);
                    isList = true;
                }
                showResults(list);
            }
        });
    }

    private void searchDB() {
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                book.setAuthor(ArrayListToString.stringToList(cursor.getString(cursor.getColumnIndex("author"))));
                book.setImage(cursor.getString(cursor.getColumnIndex("image")));
                book.setIsbn13(cursor.getString(cursor.getColumnIndex("isbn13")));
                addToList(list, book);
            } while (cursor.moveToNext());
        }
        cursor.close();

        showResults(list);
    }

    void loadDetail(final String isbn) {
        String url = API.DoubanBook + isbn;
        requestBook.load(url, new OnStringListener() {
            @Override
            public void onSuccess(String result) {
                Book book = gson.fromJson(result, Book.class);

                ContentValues values = new ContentValues();

                addToList(list, book);

                startDetail(book);

                if (book.getIsbn13() != null) {
                    db.beginTransaction();
                    try {
                        values.put("title", book.getTitle());
                        values.put("author", ArrayListToString.listToString(book.getAuthor()));
                        values.put("image", book.getImage());
                        values.put("isbn13", book.getIsbn13());
                        values.put("bookmark", 0);
                        Log.d(TAG, book.getIsbn13());
                        db.insert("Book", null, values);
                        values.clear();
                        db.setTransactionSuccessful();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }
                }

                showResults(list);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    private void addToList(ArrayList<Book> list, Book book) {
        for (int i=0; i<list.size(); i++) {
            if ((book.getTitle().equals(list.get(i).getTitle()) && ArrayListToString.listToString(book.getAuthor()).equals(ArrayListToString.listToString(list.get(i).getAuthor())))) {
                return;
            }
        }
        list.add(book);
    }

    private void startDetail(Book book) {
        this.getContext().startActivity(new Intent(this.getContext(), BookDetail.class)
            .putExtra("title", book.getTitle())
            .putExtra("author", ArrayListToString.listToString(book.getAuthor()))
            .putExtra("translator", ArrayListToString.listToString(book.getTranslator()))
            .putExtra("price", book.getPrice())
            .putExtra("publisher", book.getPublisher())
            .putExtra("pubDate", book.getPubdate())
            .putExtra("pages", book.getPages())
            .putExtra("isbn", book.getIsbn13())
            .putExtra("image", book.getImage())
            .putExtra("summary", book.getSummary())
            .putExtra("tags", book.getTags()));
    }

    public void showResults(final ArrayList<Book> list) {
        if (isList) {
            if (adapter == null) {
                adapter = new BookRecyclerViewAdapter(getContext(), list);
                adapter.setOnItemClickListener(new BookRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d(TAG, "Clicked");
                        loadDetail(list.get(position).getIsbn13());
                    }
                });
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        } else {
            if (gridAdapter == null) {
                gridAdapter = new BookRecycleViewAdapterGrid(getContext(), list);
                recyclerView.setAdapter(gridAdapter);
            } else {
                recyclerView.setAdapter(gridAdapter);
                adapter.notifyDataSetChanged();
            }
        }

    }

    private class SimpleItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {

        SimpleItemTouchHelperCallback(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            db.execSQL("delete from Book where title = ?", new String[] { list.get(position).getTitle() });
            list.remove(position);
            adapter.notifyItemRemoved(position);
            recyclerView.setAdapter(adapter);
        }
    }
}
