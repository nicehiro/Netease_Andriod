package org.nicehiro.title;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by root on 16-10-21.
 */

public class ActionBar1Activity extends Activity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ActionBar1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
    }
}
