package org.nicehiro.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by root on 16-9-30.
 */

public class ShareActivity extends Activity {

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        message = (TextView) findViewById(R.id.message);

        Intent intent = getIntent();
        String content = intent.getStringExtra(Intent.EXTRA_TEXT);
        message.setText(content);
    }
}
