package org.nicehiro.handlertest;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by hiro on 16-12-2.
 */
public class BlockAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    public BlockAdapter(Activity activity) {
        this.mInflater = activity.getLayoutInflater();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        for (int i=0; i<10000; i++) {
            //TODO
        }

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_block_item, null);
            TextView textView = (TextView) convertView.findViewById(R.id.block_item_name);

            convertView.setTag(new ViewHolder(textView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mTextView.setText(Integer.toString(position));

        return convertView;
    }

    private static class ViewHolder {
        TextView mTextView;

        public ViewHolder(TextView textView) {
            this.mTextView = textView;
        }
    }
}
