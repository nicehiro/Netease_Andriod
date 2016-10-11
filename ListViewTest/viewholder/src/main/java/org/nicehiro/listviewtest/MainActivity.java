package org.nicehiro.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        List<Message> data = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            String time = Calendar.getInstance().getTime().toLocaleString();

            data.add(new Message(
                    R.drawable.icon_contact,
                    "Friend" + i,
                    "I'm a text view" + i,
                    time
            ));
        }

        ListAdapter adapter = new MessageAdapter(this, data);
        listView.setAdapter(adapter);
    }
}
