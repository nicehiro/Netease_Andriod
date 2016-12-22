package org.nicehiro.broadcasttest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkStateChangeReceiver mNetworkStateChangeReceiver;
    private IntentFilter mIntentFilter;
    StudentReceiver mStudentReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.student).setOnClickListener(this);
        findViewById(R.id.build).setOnClickListener(this);
        findViewById(R.id.next_page).setOnClickListener(this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkStateChangeReceiver = new NetworkStateChangeReceiver();
        registerReceiver(mNetworkStateChangeReceiver, mIntentFilter);

        Button button0 = (Button) findViewById(R.id.student);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("org.nicehiro.student.after_school");
        mStudentReceiver= new StudentReceiver(button0);
        registerReceiver(mStudentReceiver, mIntentFilter);

    }

    private void build() {
        Intent intent = new Intent("org.nicehiro.build");
        intent.putExtra("type", "school");
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.student:
                break;
            case R.id.build:
                build();
                break;
            case R.id.next_page:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkStateChangeReceiver);
        unregisterReceiver(mStudentReceiver);
    }
}
