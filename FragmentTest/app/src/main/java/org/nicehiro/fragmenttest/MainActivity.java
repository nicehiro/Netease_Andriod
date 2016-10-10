package org.nicehiro.fragmenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.make_fragment).setOnClickListener(this);
        findViewById(R.id.make_fragment_with_arg).setOnClickListener(this);
        findViewById(R.id.make_fragment_with_state).setOnClickListener(this);
        findViewById(R.id.make_fragment_with_trans).setOnClickListener(this);
        findViewById(R.id.make_fragment_with_comm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.make_fragment:
                intent = new Intent(this, MallActivity.class);
                startActivity(intent);
                break;
            case R.id.make_fragment_with_arg:
                intent = new Intent(this, MallActivityWithArg.class);
                startActivity(intent);
                break;
            case R.id.make_fragment_with_trans:
                intent = new Intent(this, MallActivityWithTrans.class);
                startActivity(intent);
                break;
            case R.id.make_fragment_with_state:
                intent = new Intent(this, MallActivityWithState.class);
                startActivity(intent);
                break;
            case R.id.make_fragment_with_comm:
                intent = new Intent(this, MallActivityWithComm.class);
                startActivity(intent);
                break;
        }
    }
}
