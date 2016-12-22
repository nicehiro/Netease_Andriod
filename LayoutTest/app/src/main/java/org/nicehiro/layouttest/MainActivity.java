package org.nicehiro.layouttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll);

        TextView textView = (TextView) findViewById(R.id.next);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollPic.class);
                startActivity(intent);
            }
        });

        TextView textView1 = (TextView) findViewById(R.id.show_call_activity);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowPhoneInterface.class);
                startActivity(intent);
            }
        });

        TextView textView2 = (TextView) findViewById(R.id.nothing);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Nothing.class);
                startActivity(intent);
            }
        });
    }
}
