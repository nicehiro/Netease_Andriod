package org.nicehiro.lodertest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by hiro on 16-12-5.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private SimpleCursorAdapter mAdapter;
    private CursorLoader mCursorLoader;

    private Button nextPage;

    private final String[] CONTACT_NAME_PROJECTION = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };

    private static final int CONTACT_NAME_LOADER_ID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initAdapter();

        mCursorLoader = new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                CONTACT_NAME_PROJECTION, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        mCursorLoader.registerListener(CONTACT_NAME_LOADER_ID, new Loader.OnLoadCompleteListener<Cursor>() {
            @Override
            public void onLoadComplete(Loader<Cursor> loader, Cursor data) {
                Toast.makeText(SecondActivity.this, "onLoadComplete", Toast.LENGTH_SHORT).show();
                if (mAdapter != null) {
                    mAdapter.swapCursor(data);
                }
            }
        });
        mCursorLoader.startLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCursorLoader.cancelLoad();
    }

    private void initAdapter() {
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[] {android.R.id.text1}, 0);
        ListView listView = (ListView) findViewById(R.id.contact_name);
        if (listView != null) {
            listView.setAdapter(mAdapter);
        }

        nextPage = (Button) findViewById(R.id.next_page);
        nextPage.setOnClickListener(this);
    }

    private void nextPage() {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_page:
                nextPage();
                break;
        }
    }
}
