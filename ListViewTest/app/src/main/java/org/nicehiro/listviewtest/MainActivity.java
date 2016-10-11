package org.nicehiro.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);

        List<Object> messageList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                TextMessage message = new TextMessage("I'm text message" + i);
                messageList.add(message);
            } else {
                ImageMessage imageMessage = new ImageMessage(R.drawable.image);
                messageList.add(imageMessage);
            }
        }

        MessageAdapter adapter = new MessageAdapter(messageList);
        listView.setAdapter(adapter);
    }
}
