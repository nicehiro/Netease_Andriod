package org.nicehiro.lodertest;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by hiro on 16-12-5.
 */

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    private static final int CONTACT_NAME_LOADER_ID = 0;
    private static final String[] CONTACT_NAME_PROJECTION = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initAdapter();

        Bundle args = new Bundle();
        args.putString("key", "value");
        getLoaderManager().initLoader(CONTACT_NAME_LOADER_ID, args, ThirdActivity.this);
    }

    private void initAdapter() {
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, new String[] {ContactsContract.Contacts.DISPLAY_NAME },
                new int[] {android.R.id.text1}, 0);
        ListView listView = (ListView) findViewById(R.id.contact_name);
        if (listView != null) {
            listView.setAdapter(mAdapter);
            mAdapter.swapCursor(null);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Toast.makeText(this, "onCreateLoader args " + bundle.toString(),
                Toast.LENGTH_SHORT).show();

        return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                CONTACT_NAME_PROJECTION, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC") {
            @Override
            public Cursor loadInBackground() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return super.loadInBackground();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Toast.makeText(this, "onLoadFinished", Toast.LENGTH_SHORT).show();
        if (mAdapter != null) {
            mAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Toast.makeText(this, "onLoaderReset", Toast.LENGTH_SHORT).show();
        if (mAdapter != null) {
            mAdapter.swapCursor(null);
        }
    }
}
