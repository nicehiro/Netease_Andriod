package org.nicehiro.ormtest.ormsql;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.nicehiro.ormtest.R;

import java.util.List;

/**
 * Created by hiro on 16-11-15.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactList;

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public MyAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList == null ? 0 : contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return (Integer) i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView != null) {
            textView = (TextView) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.item_layout, null);
            textView = (TextView) convertView.findViewById(R.id.textview_name);
            convertView.setTag(textView);
        }

        textView.setText(contactList.get(position).getName());
        return convertView;
    }
}
