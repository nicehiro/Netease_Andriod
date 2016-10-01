package org.nicehiro.logintest;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoCompleteTextView inputUserName;
    private View close;
    private TextView message;

    private static final int USERID_MAX_LENGTH = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Login).setOnClickListener(this);
        findViewById(R.id.close).setOnClickListener(this);
        message = (TextView) findViewById(R.id.message);
        close = findViewById(R.id.close);
        inputUserName = (AutoCompleteTextView)findViewById(R.id.inputUserName);
        //auto mouse focus
        inputUserName.requestFocus();
        //set adapter to autocomplete when you put two words
        inputUserName.setAdapter(new MyAdapter());
        inputUserName.setThreshold(2);

        //set user name's length limited
        inputUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > USERID_MAX_LENGTH) {
                    message.setText("user name beyond the max length");
                    message.setVisibility(View.VISIBLE);
                } else {
                    message.setVisibility(View.INVISIBLE);
                }
                close.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });

        //to run a new progress to InputMethod
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) inputUserName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(inputUserName, 0);
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                inputUserName.setText(null);
                break;
            case R.id.Login:
                break;
        }
    }

    class MyAdapter extends BaseAdapter implements Filterable {

        private String[] mArrays = getResources().getStringArray(R.array.userIdSuffix);
        private ArrayList<String> mdata = new ArrayList<>();

        @Override
        public int getCount() {
            return mdata.size();
        }

        @Override
        public Object getItem(int position) {
            return mdata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(MainActivity.this);
            } else
                textView = (TextView) convertView;

            textView.setText(getItem(position).toString());
            return textView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    List<String> newData = new ArrayList<>();
                    if (constraint != null && !constraint.toString().contains("@")) {
                        for (String item : mArrays) {
                            newData.add(constraint + item);
                        }
                    }
                    results.values = newData;
                    results.count = newData.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mdata = (ArrayList) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}
