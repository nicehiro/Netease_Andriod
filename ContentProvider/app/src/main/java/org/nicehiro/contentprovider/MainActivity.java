package org.nicehiro.contentprovider;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout container;
    private ContactsObserver contactsObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.container);
        findViewById(R.id.insert_contact).setOnClickListener(this);

        contactsObserver = new ContactsObserver(new Handler());

        getContentResolver().registerContentObserver(ContactsContract.AUTHORITY_URI, true, contactsObserver);

        loadContacts();
    }

    private void loadContacts() {
        ContentResolver contentResolver = getContentResolver();

        String[] projection = {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID,
        };

        //查询通讯录的表到 cursor
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);

        if (null != cursor) {
            container.removeAllViews();

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                TextView textView = new TextView(this);
                textView.setText(name);
                textView.setTag(id);
                textView.setOnClickListener(this);

                container.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
            }

            cursor.close();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.insert_contact) {
            insertContact();
            return;
        }

        String id = (String) view.getTag();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getContentResolver().unregisterContentObserver(contactsObserver);
    }

    private class ContactsObserver extends ContentObserver {

        public ContactsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            loadContacts();
        }
    }

    public void insertContact () {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
    }
}
