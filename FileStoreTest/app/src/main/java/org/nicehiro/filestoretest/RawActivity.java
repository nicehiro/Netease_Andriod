package org.nicehiro.filestoretest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 16-11-8.
 */

public class RawActivity extends AppCompatActivity implements View.OnClickListener{

    private MyListViewAdapter adapter;

    private JSONArray hzCitys;

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row);

        findViewById(R.id.readFromRow).setOnClickListener(this);
        findViewById(R.id.readFromAsset).setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listview_row);
        adapter = new MyListViewAdapter(hzCitys, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readFromRow:
                readFileFromRow(this);
                break;
            case R.id.readFromAsset:
                readFileFromAsset(this);
        }
    }

    private void readFileFromRow(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.city);
        String rawContent = Util.getStringStream(inputStream);

        Log.i("raw", rawContent);

        try {
            JSONObject citys = new JSONObject(rawContent);
            hzCitys = citys.getJSONObject("浙江").getJSONArray("杭州市");
            adapter.setHzCitys(hzCitys);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void readFileFromAsset(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("city.json");
            String assetContent = Util.getStringStream(inputStream);
            Log.i("asset", assetContent);

            JSONObject citys = new JSONObject(assetContent);
            hzCitys = citys.getJSONObject("浙江").getJSONArray("湖州市");
            adapter.setHzCitys(hzCitys);
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
