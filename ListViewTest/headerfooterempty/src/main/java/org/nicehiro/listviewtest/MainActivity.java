package org.nicehiro.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<View> headerViewList = new LinkedList<>();
    private List<View> footerViewList = new LinkedList<>();

    private List<Message> data = new ArrayList<>();
    private BaseAdapter baseAdapter = new MessageAdapter(this, data);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        fillData(data);

        View headerView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.header_view, listView, false);
        listView.addHeaderView(headerView);
        headerViewList.add(headerView);

        listView.setAdapter(baseAdapter);

        //no message show
        final ViewGroup listViewContainer = (ViewGroup) findViewById(R.id.listview_container);
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view, listViewContainer, false);
        listViewContainer.addView(emptyView);
        listView.setEmptyView(emptyView);

        Button addHeader = (Button) findViewById(R.id.add_header);
        addHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View headerView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.header_view, listView, false);

                listView.addHeaderView(headerView);
                headerViewList.add(headerView);
            }
        });

        Button removeHeader = (Button) findViewById(R.id.remove_header);
        removeHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (headerViewList.size() > 0) {
                    listView.removeHeaderView(headerViewList.get(0));
                    headerViewList.remove(0);
                }
            }
        });

        final Button addFooter = (Button) findViewById(R.id.add_footer);
        addFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View footerView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.footer_view, listView, false);

                listView.addFooterView(footerView);
                footerViewList.add(footerView);
            }
        });

        Button removeFooter = (Button) findViewById(R.id.remove_footer);
        removeFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (footerViewList.size() > 0) {
                    listView.removeFooterView(footerViewList.get(0));
                    footerViewList.remove(0);
                }
            }
        });

        Button clearData = (Button) findViewById(R.id.clear_data);
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                baseAdapter.notifyDataSetChanged();
            }
        });

        Button restoreData = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                fillData(data);
                baseAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i("listener",
                        "onScrollStateChanged scrollState:" + scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    Toast.makeText(
                            MainActivity.this,
                            "Auto reload data",
                            Toast.LENGTH_SHORT
                    ).show();

                    int count = data.size();
                    for (int i = count; i < count+100; i++) {
                        data.add(new Message("I'm the text message" + i));
                    }

                    baseAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void fillData(List<Message> data) {
        for (int i = 0; i < 100; i++) {
            data.add(new Message("I'm the text message" + i));
        }
    }
}
