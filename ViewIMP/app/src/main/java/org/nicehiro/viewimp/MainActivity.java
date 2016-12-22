package org.nicehiro.viewimp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SampleDecorView sampleDecorView;

    private static final int WIDTH = 300;

    private static final int HEIGHT = 800;

    private static final int MOVE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_decor_fg).setOnClickListener(this);
        findViewById(R.id.hide_decor_fg).setOnClickListener(this);
        findViewById(R.id.add_view_to_decor).setOnClickListener(this);
        findViewById(R.id.remove_view_from_decor).setOnClickListener(this);
        findViewById(R.id.simple_window).setOnClickListener(this);
        findViewById(R.id.pupup_window).setOnClickListener(this);

        sampleDecorView = new SampleDecorView(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_decor_fg:
                sampleDecorView.fg(true);
                break;
            case R.id.hide_decor_fg:
                sampleDecorView.fg(false);
                break;
            case R.id.add_view_to_decor:
                sampleDecorView.view(true);
                break;
            case R.id.remove_view_from_decor:
                sampleDecorView.view(false);
                break;
            case R.id.simple_window:
                new SampleSimpleWindow(this).show(WIDTH, HEIGHT, MOVE);
                break;
            case R.id.pupup_window:
                new SamplePopupWindow(this, view).show(WIDTH, HEIGHT, MOVE);
                break;
        }
    }
}
