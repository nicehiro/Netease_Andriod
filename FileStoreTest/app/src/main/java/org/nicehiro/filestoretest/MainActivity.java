package org.nicehiro.filestoretest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_row).setOnClickListener(this);
        findViewById(R.id.start_internal_file).setOnClickListener(this);
        findViewById(R.id.start_internal_cache).setOnClickListener(this);
        findViewById(R.id.start_extend_storage).setOnClickListener(this);
        findViewById(R.id.start_external_storage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.start_row:
                intent = new Intent(this, RawActivity.class);
                startActivity(intent);
                break;
            case R.id.start_internal_file:
                intent = new Intent(this, InternalFileActivity.class);
                startActivity(intent);
                break;
            case R.id.start_internal_cache:
                intent = new Intent(this, InternalCacheActivity.class);
                startActivity(intent);
                break;
            case R.id.start_extend_storage:
                intent = new Intent(this, ExtendStorageActivity.class);
                startActivity(intent);
                break;
            case R.id.start_external_storage:
                intent = new Intent(this, ExternalStorageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
