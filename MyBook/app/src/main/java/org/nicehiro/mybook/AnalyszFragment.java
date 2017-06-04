package org.nicehiro.mybook;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hiro on 17-3-16.
 */

public class AnalyszFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private TextView tvTotal;
    private TextView tvMarked;
    private TextView tvNotMark;
    private TextView tvTags;
    private PieView pieView;
    private SearchView searchView;
    private Toolbar toolbar;
    private TextView tv_result;
    private TextView tv_price;
    private TextView tv_publisher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysz, container, false);
        dbHelper = new DatabaseHelper(getContext(), "History.db", null, 1);
        db = dbHelper.getWritableDatabase();
        initView(view);
        HashMap<String, Integer> map = classifyByTags();
        draw(map, pieView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieView.invalidate();
//        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
//        inflater.inflate(R.menu.search_menu, menu);
        // setSearchView(menu);

//        MenuItem searchItem = menu.findItem(R.id.search_view);
//
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(
//                    new ComponentName(getActivity().getApplicationContext(), SearchViewResult.class))
//            );
//        }
    }

    private void initView(View view) {
        tvTotal = (TextView) view.findViewById(R.id.book_total_number);
        tvMarked = (TextView) view.findViewById(R.id.book_marked_number);
        tvNotMark = (TextView) view.findViewById(R.id.book_not_mark_number);
        tvTags = (TextView) view.findViewById(R.id.classify_by_tags);
        pieView = (PieView) view.findViewById(R.id.pieview);
        tv_result = (TextView) view.findViewById(R.id.search_result);
        tv_price = (TextView) view.findViewById(R.id.price_sub);
        tv_publisher = (TextView) view.findViewById(R.id.publisher_max);
        toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        searchView = (SearchView) view.findViewById(R.id.search_view);
        setSearchView();

        tvTotal.setText("总书数目： " + getBookTotalNumber());
        tvMarked.setText("Marked 书数目： " + getBookMarkedNumber());
        tvNotMark.setText("NotMarked 书数目： " + getBookNotMarkNumber());

        HashMap<String, Integer> map = classifyByTags();
        String result = "";
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            result += item.getKey() + ": " + item.getValue() + "\n";
        }
        tvTags.setText(result);

        tv_publisher.setText(getPublisherMax());
        tv_price.setText(new StringBuilder().append(getPriceSub()).append("").toString());
    }

    private int getBookTotalNumber() {
        Cursor cursor = db.rawQuery("select count(*) as total from Book", null);
        int total = -1;
        while (cursor.moveToNext()) {
            total = cursor.getInt(cursor.getColumnIndex("total"));
        }
        return total;
    }

    private int getBookMarkedNumber() {
        Cursor cursor = db.rawQuery("select count(*) as marked from Book where bookmark = ?", new String[] {"1"});
        int marked = -1;
        while (cursor.moveToNext()) {
            marked = cursor.getInt(cursor.getColumnIndex("marked"));
        }
        return marked;
    }

    private int getBookNotMarkNumber() {
        Cursor cursor = db.rawQuery("select count(*) as notmark from Book where bookmark = ?", new String[] {"0"});
        int notmark = -1;
        while (cursor.moveToNext()) {
            notmark = cursor.getInt(cursor.getColumnIndex("notmark"));
        }
        return notmark;
    }

    private HashMap<String, Integer> classifyByTags() {
        Cursor cursor = db.rawQuery("select tags, count(*) from Book group by tags", null);
        HashMap<String, Integer> map = new HashMap<>();
        while (cursor.moveToNext()) {
            String tag = cursor.getString(cursor.getColumnIndex("tags"));
            int times = cursor.getInt(cursor.getColumnIndex("count(*)"));
            map.put(tag, times);

            Log.d("Tags: ", tag + " " + times + "\n");
        }
        return map;
    }

    private void draw(HashMap<String, Integer> map, PieView view) {
        ArrayList<PieData> data = new ArrayList<>();
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            String tag = item.getKey();
            int value = item.getValue();
            PieData pie = new PieData(tag, value);
            data.add(pie);
        }

        view.setData(data);
        pieView.invalidate();
    }

    private void setSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor cursor = db.rawQuery("select title, author, tags, bookmark from Book where title like ?",
                        new String[] {"%"+query+"%"});
                String result = "title\t author\t tags\t bookmark\t \n";
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String tags = cursor.getString(cursor.getColumnIndex("tags"));
                    int bookmark = cursor.getInt(cursor.getColumnIndex("bookmark"));
                    result += title + "\t " + author + "\t " + tags + "\t " + bookmark + "\t \n";
                }
                tv_result.setText(result);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private float getPriceSub() {
        Cursor cursor = db.rawQuery("select sum(price) from Book", null);
        float ret = 0;
        while (cursor.moveToNext()) {
            ret += cursor.getFloat(cursor.getColumnIndex("sum(price)"));
        }
        return ret;
    }

    private String getPublisherMax() {
        Cursor cursor = db.rawQuery("select publisher, count(*) from Book group by publisher", null);
        String ret = "";
        if (cursor.moveToNext()) {
            ret = cursor.getString(cursor.getColumnIndex("publisher"));
        }
        return ret;
    }
}
