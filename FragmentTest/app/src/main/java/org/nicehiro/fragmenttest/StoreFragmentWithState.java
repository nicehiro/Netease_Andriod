package org.nicehiro.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 16-10-9.
 */

public class StoreFragmentWithState extends StoreFragmentWithArg implements View.OnClickListener{

    private int viewCount;
    private TextView tvViewCount;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_view_count:
                viewCount ++;
                updateViewCount();
                break;
        }
    }

    private void updateViewCount() {
        tvViewCount.setText("" + viewCount);
    }

    protected int getViewCount() {
        return viewCount;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvViewCount = (TextView) view.findViewById(R.id.store_view_count);
        tvViewCount.setOnClickListener(this);

        updateViewCount();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_state, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            viewCount = savedInstanceState.getInt("viewCount", 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("viewCount", viewCount);
    }
}
