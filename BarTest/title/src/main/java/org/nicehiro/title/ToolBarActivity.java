package org.nicehiro.title;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by root on 16-10-21.
 */

public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ToolBarActivity.class);
        context.startActivity(intent);
    }

    public void setToolBar(Toolbar toolBar) {
        toolBar.setTitle("ToolBar");
        toolBar.setSubtitle("toolbar");
        toolBar.setLogo(R.drawable.logo);
        toolBar.inflateMenu(R.menu.action_menu);
    }
}
