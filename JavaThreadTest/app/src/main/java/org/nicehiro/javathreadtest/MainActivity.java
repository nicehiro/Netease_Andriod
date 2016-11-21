package org.nicehiro.javathreadtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyRestaurant myRestaurant;
    private List<String> mMessage = new ArrayList<>();
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.open_door).setOnClickListener(this);
        findViewById(R.id.close_door).setOnClickListener(this);
        findViewById(R.id.add_people).setOnClickListener(this);

        ListView listView = (ListView) findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<String>(this, R.layout.item_view, R.id.message, mMessage);
        listView.setAdapter(mAdapter);

        Logger logger = new Logger(mAdapter, mMessage);
        myRestaurant = new MyRestaurant(logger);
    }

    private void openRestaurant() {
        myRestaurant.open();
    }

    private void closeRestaurant() {
        myRestaurant.close();
    }

    private void customerIn() {
        myRestaurant.customerIn();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_door:
                openRestaurant();
                break;
            case R.id.close_door:
                closeRestaurant();
                break;
            case R.id.add_people:
                customerIn();
                break;
        }
    }

    public class Logger {
        private ArrayAdapter mAdapter;
        private List<String> mMessages;

        public Logger(ArrayAdapter mAdapter, List<String> mMessages) {
            this.mAdapter = mAdapter;
            this.mMessages = mMessages;
        }

        public void log(final String tag, final String msg) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMessages.add(tag + ": " + msg);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        public void clear() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMessages.clear();
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
