package org.nicehiro.listviewtest;

import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText modifyPosition;
    private View modifyButton;

    List<Message> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.list_view);

        data = new ArrayList<>(100);

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

        modifyPosition = (EditText) findViewById(R.id.modify_position);
        modifyButton = findViewById(R.id.modify_message);

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(modifyPosition.getText().toString());
                Message message = data.get(position);
                message.setContent("this is modified answer" + position);

                paticalRefresh(listView, position);
            }
        });
    }

    private void paticalRefresh(ListView listView, int position) {
        if (position >= listView.getFirstVisiblePosition()
                && position <= listView.getLastVisiblePosition()) {
            int childIndex = position - listView.getFirstVisiblePosition();
            View child = listView.getChildAt(childIndex);

            if (child.getTag() instanceof MessageAdapter.ViewHolder) {
                ((MessageAdapter.ViewHolder) child.getTag()).refreshContent(data.get(position).getContent());
            }
        }
    }
}
