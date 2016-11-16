package org.nicehiro.ormtest.ormsql;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.nicehiro.ormtest.R;
import org.nicehiro.ormtest.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiro on 16-11-15.
 */

public class OrmSqlActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Contact> contactList;
    private MyAdapter myAdapter;

    private ListView listViewContacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm_sql);

        findViewById(R.id.orm_insert).setOnClickListener(this);
        findViewById(R.id.orm_query).setOnClickListener(this);

        listViewContacts = (ListView) findViewById(R.id.orm_listview_contacts);
        myAdapter = new MyAdapter(this, contactList);
        listViewContacts.setAdapter(myAdapter);
    }

    private void insertORMContact() {
        List<Contact> contacts = new ArrayList<>();

        for (int i=0; i<50; i++) {
            Contact contact = new Contact();
            contact.setUId(Util.RandomString(8));
            contact.setYId(Util.RandomString(8));
            contact.setName(Util.RandomString(6));
            contact.setGender(i%2);
            contact.setMobile(Util.RandomNumber(13));

            contacts.add(contact);
        }

        OrmHelper.getInstance(this).insertORMContacts(contacts);
    }

    private void queryORMContact() {
        contactList = OrmHelper.getInstance(this).queryORMContact(0, 25);
        myAdapter.setContactList(contactList);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orm_insert:
                insertORMContact();
                break;
            case R.id.orm_query:
                queryORMContact();
                break;
        }
    }
}
