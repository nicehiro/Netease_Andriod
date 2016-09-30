package org.nicehiro.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by root on 16-9-30.
 */

public class ActivityC extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void startActivityC(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ActivityC.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void startActivityB(View view) {
        Intent intent = new Intent();
        intent.setClass(ActivityC.this, ActivityB.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void markCall(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    public void callNum(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:17826856902"));
        startActivity(intent);
    }

    public void shareTextContent(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        intent.setType("text/plain");
        startActivity(intent);
    }

}
