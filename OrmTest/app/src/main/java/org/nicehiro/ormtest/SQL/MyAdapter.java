package org.nicehiro.ormtest.SQL;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.nicehiro.ormtest.R;

import java.util.List;

/**
 * Created by hiro on 16-11-14.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactList;

    public MyAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    public void setContactList(List<Contact> contactList) {
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
    public View getView(int i, View convertView, ViewGroup parent) {
        TextView textViewName;

        if (convertView != null) {
            textViewName = (TextView) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.item_layout, null);
            textViewName = (TextView) convertView.findViewById(R.id.textview_name);
            convertView.setTag(textViewName);
        }

        textViewName.setText(contactList.get(i).getMobile());
        return convertView;
    }
}
