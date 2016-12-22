package org.nicehiro.filestoretest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by root on 16-11-8.
 */

public class MyListViewAdapter extends BaseAdapter {

    private JSONArray hzCitys;

    private Context context;

    public void setHzCitys(JSONArray hzCitys) {
        this.hzCitys = hzCitys;
    }

    public MyListViewAdapter(JSONArray hzCitys, Context context) {
        this.hzCitys = hzCitys;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hzCitys == null ? 0 : hzCitys.length();
    }

    @Override
    public Object getItem(int position) {
        return (Integer) position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = null;

        if (convertView != null) {
            textView = (TextView) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.item_listview, null);
            textView = (TextView) convertView.findViewById(R.id.text_city);
            convertView.setTag(textView);
        }

        try {
            textView.setText(hzCitys.getString(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
