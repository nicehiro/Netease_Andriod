package org.nicehiro.ormtest.SQL;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.nicehiro.ormtest.R;

import java.util.List;

/**
 * Created by hiro on 16-11-14.
 */

public class SQLActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Contact> contactList;
    private FriendOpenHelper friendOpenHelper;

    private ListView listViewContacts;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_sql);

        findViewById(R.id.sql_insert).setOnClickListener(this);
        findViewById(R.id.sql_query).setOnClickListener(this);

        listViewContacts = (ListView) findViewById(R.id.listview_contacts);
        myAdapter = new MyAdapter(this, contactList);
        listViewContacts.setAdapter(myAdapter);

        friendOpenHelper = new FriendOpenHelper(this);
    }

    private void insertSQLContact() {
        for (int i=0; i<50; i++) {
            Contact contact = new Contact();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sql_insert:
                break;
            case R.id.sql_query:
                break;
        }
    }
}
