package org.nicehiro.ormtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.nicehiro.ormtest.SQL.SQLActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startSQL).setOnClickListener(this);
        findViewById(R.id.startORM).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.startSQL:
                intent = new Intent(this, SQLActivity.class);
                startActivity(intent);
                break;
            case R.id.startORM:
                break;
        }
    }
}
