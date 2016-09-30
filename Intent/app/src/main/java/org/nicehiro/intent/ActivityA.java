package org.nicehiro.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityA extends AppCompatActivity {

    private static final int REQUEST_CODE = 10000;

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        message = (TextView) findViewById(R.id.message);
    }

    public void startActivityB(View view) {
        Intent intent = new Intent(ActivityA.this, ActivityB.class);
        intent.putExtra("Message", "this is a message from Activity A");
        startActivity(intent);
    }

    public void startActivityBForResult(View view) {
        Intent intent = new Intent();
        intent.setClass(ActivityA.this, ActivityB.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String msg = data.getStringExtra("Message");
                    message.setText("Result OK" + msg);
                    break;
                case RESULT_CANCELED:
                    message.setText("Result Canceked");
                    break;
            }
        }
    }
}
