package org.nicehiro.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageDatabaseHelper databaseHelper;

    private TextView searchResult;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new MessageDatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.remove).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.search).setOnClickListener(this);

        searchResult = (TextView) findViewById(R.id.query_result);
    }

    private void insert() {
        Message message = new Message(
                id ++,
                "hiro",
                "mike",
                new Date().getTime(),
                "hello",
                Message.State.SUCCESS
        );

        databaseHelper.insertMessage(message);
    }

    private void remove() {
        databaseHelper.removeMessage(3);
    }

    private void update() {
        databaseHelper.updateMessage(2, Message.State.FAIL);
    }

    private void search() {
        List<Message> messageList = databaseHelper.searchAllMessage();
        StringBuilder stringBuilder = new StringBuilder();
        for (Message message : messageList) {
            stringBuilder.append(message.toString()).append("\n");
        }
        searchResult.setText(stringBuilder.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.remove:
                remove();
                break;
            case R.id.update:
                update();
                break;
            case R.id.search:
                search();
                break;
        }
    }
}
