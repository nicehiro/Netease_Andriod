package org.nicehiro.title;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.custom_title).setOnClickListener(this);
        findViewById(R.id.action_bar_title).setOnClickListener(this);
        findViewById(R.id.action_bar_title1).setOnClickListener(this);
        findViewById(R.id.tool_bar_title).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_title:
                CustomTitleActivity.start(this);
                break;
            case R.id.action_bar_title:
                ActionBarActivity.start(this);
                break;
            case R.id.action_bar_title1:
                ActionBar1Activity.start(this);
                break;
            case R.id.tool_bar_title:
                ToolBarActivity.start(this);
                break;
        }
    }
}
