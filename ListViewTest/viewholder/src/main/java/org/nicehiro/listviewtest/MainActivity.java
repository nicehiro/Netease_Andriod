package org.nicehiro.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);

        final List<Message> data = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            String time = Calendar.getInstance().getTime().toLocaleString();

            data.add(new Message(
                    R.drawable.icon_contact,
                    "Friend" + i,
                    "I'm a text view" + i,
                    time
            ));
        }

        final BaseAdapter adapter = new MessageAdapter(this, data);
        listView.setAdapter(adapter);

        //for notifyDataSetChanged() test
        findViewById(R.id.add_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = Calendar.getInstance().getTime().toLocaleString();

                data.add(0, new Message(
                        R.drawable.icon_contact,
                        "Friend",
                        "I'm a text view",
                        time
                ));
                adapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.delete_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size()>0) {
                    data.remove(0);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
