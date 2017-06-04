package org.nicehiro.mybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by hiro on 17-5-20.
 */

public class SearchViewResult extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        tvResult = (TextView) findViewById(R.id.search_result);
        Intent intent = getIntent();
        handle(intent);
    }


    private void handle(Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
            Bundle bundle = intent.getExtras();
        }
    }
}
