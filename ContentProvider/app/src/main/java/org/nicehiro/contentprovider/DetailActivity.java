package org.nicehiro.contentprovider;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hiro on 16-11-13.
 */

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;

    private String contactId;
    private static final int RQ_EDIT_CONTACT = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViewById(R.id.edit_contact).setOnClickListener(this);
        findViewById(R.id.delete_contact).setOnClickListener(this);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvEmail = (TextView) findViewById(R.id.tv_email);

        Intent intent = getIntent();
        contactId = intent.getStringExtra("id");

        showContact();
    }

    private void showContact() {
        String[] projection = {
                ContactsContract.Data.DATA1,
                ContactsContract.Data.MIMETYPE
        };

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection,
                ContactsContract.Data.CONTACT_ID + "=" + contactId,
                null, null);

        if (null != cursor) {
            while (cursor.moveToNext()) {
                String value = cursor.getString(cursor.getColumnIndex(projection[0]));
                String type = cursor.getString(cursor.getColumnIndex(projection[1]));

                if (ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                        .equals(type)) {
                    tvName.setText(value);
                } else if (ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                        .equals(type)) {
                    tvPhone.setText(value);
                } else if (ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
                        .equals(type)) {
                    tvEmail.setText(value);
                }
            }

            cursor.close();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_contact:
                editContact();
                break;
            case R.id.delete_contact:
                deleteContact();
                break;
        }
    }

    private void deleteContact() {
        ContentResolver contentResolver = getContentResolver();
        contentResolver.delete(ContactsContract.Data.CONTENT_URI,
                ContactsContract.Data.CONTACT_ID + "=" + contactId, null);

        contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI,
                ContactsContract.RawContacts.CONTACT_ID + "=" + contactId, null);

        finish();
    }

    private void editContact() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("id", contactId);
        startActivityForResult(intent, RQ_EDIT_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RQ_EDIT_CONTACT == requestCode && RESULT_OK == resultCode) {
            showContact();
        }
    }
}
