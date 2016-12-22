package org.nicehiro.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by root on 16-9-30.
 */

public class ActivityB extends Activity{

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        message = (TextView) findViewById(R.id.message);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("Message");

        if (!TextUtils.isEmpty(msg))
            message.setText(msg);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void startActivityC (View view) {
        Intent intent = new Intent(ActivityB.this, ActivityC.class);
        startActivity(intent);
    }

    public void returnAWithOK(View view) {
        Intent intent = new Intent();
        intent.putExtra("Message", "this message return from Activity B");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void returnBWithCanceled(View view) {
        setResult(RESULT_CANCELED, null);
        finish();
    }
}
