package org.nicehiro.contentprovider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by hiro on 16-11-13.
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText inputName;
    private EditText inputPhone;
    private EditText inputEmail;

    private String contactId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        inputName = (EditText) findViewById(R.id.et_name);
        inputPhone = (EditText) findViewById(R.id.et_phone);
        inputEmail = (EditText) findViewById(R.id.et_email);

        findViewById(R.id.save_contact).setOnClickListener(this);

        Intent intent = getIntent();
        contactId = intent.getStringExtra("id");

        showContact();
    }

    private void showContact() {
        if (null == contactId) {
            return;
        }

        //sql: select projection 指定查询的列名
        String[] projection = {
                ContactsContract.Data.DATA1,
                ContactsContract.Data.MIMETYPE
        };

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, projection,
                ContactsContract.Data.CONTACT_ID + "=" + contactId, null, null);

        if (null != cursor) {
            while (cursor.moveToNext()) {
                String value = cursor.getString(cursor.getColumnIndex(projection[0]));
                String type = cursor.getString(cursor.getColumnIndex(projection[1]));

                //name
                if (ContactsContract.CommonDataKinds
                        .StructuredName.CONTENT_ITEM_TYPE.equals(type)) {
                    inputName.setText(value);
                //phone
                } else if (ContactsContract.CommonDataKinds
                        .Phone.CONTENT_ITEM_TYPE.equals(type)) {
                    inputPhone.setText(value);
                //email
                } else if (ContactsContract.CommonDataKinds
                        .Email.CONTENT_ITEM_TYPE.equals(type)) {
                    inputEmail.setText(value);
                }
            }

            cursor.close();
        }
    }

    public void saveContact () {
        String name = inputName.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();

        ContentResolver contentResolver = getContentResolver();

        if (null != contactId) {
            ContentValues contentValues = new ContentValues();

            String condition = ContactsContract.Data.CONTACT_ID + "=? AND "
                    + ContactsContract.Data.MIMETYPE + "=?";

            contentValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
            contentResolver.update(ContactsContract.Data.CONTENT_URI, contentValues,
                    condition, new String[] {contactId,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE});
            contentValues.clear();

            contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
            contentResolver.update(ContactsContract.Data.CONTENT_URI, contentValues,
                    condition, new String[] {contactId,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE});
            contentValues.clear();

            contentValues.put(ContactsContract.CommonDataKinds.Email.ADDRESS, email);
            contentResolver.update(ContactsContract.Data.CONTENT_URI, contentValues,
                    condition, new String[] {contactId,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE});
            contentValues.clear();
        } else {
            ArrayList<ContentProviderOperation> operationArrayList = new ArrayList<>();

            ContentProviderOperation contentProviderOperation =
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "").build();
            operationArrayList.add(contentProviderOperation);

            contentProviderOperation = ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).build();
            operationArrayList.add(contentProviderOperation);

            contentProviderOperation = ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).build();
            operationArrayList.add(contentProviderOperation);

            contentProviderOperation = ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).build();
            operationArrayList.add(contentProviderOperation);

            try {
                contentResolver.applyBatch(ContactsContract.AUTHORITY, operationArrayList);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (OperationApplicationException e) {
                e.printStackTrace();
            }
        }

        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_contact:
                saveContact();
                break;
        }
    }
}
