package org.nicehiro.projectonetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_1).setOnClickListener(this);
        findViewById(R.id.test_2).setOnClickListener(this);
        findViewById(R.id.test_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_1:
                intent = new Intent(this, MyAnimation.class);
                startActivity(intent);
                break;
            case R.id.test_2:
                intent = new Intent(this, RenderPathActivity.class);
                startActivity(intent);
                break;
            case R.id.test_3:
                intent = new Intent(this, ViewTestThree.class);
                startActivity(intent);
                break;
        }
    }
}
