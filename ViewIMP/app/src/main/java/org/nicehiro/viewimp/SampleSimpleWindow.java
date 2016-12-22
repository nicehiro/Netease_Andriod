package org.nicehiro.viewimp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by hiro on 16-12-22.
 */

public class SampleSimpleWindow implements View.OnClickListener {

    private Context context;

    private SimpleWindow simpleWindow;

    private int width = 0;

    private int height = 0;

    private int move = 0;

    public SampleSimpleWindow(Context context) {
        this.context = context;
        this.simpleWindow = new SimpleWindow(context);
    }

    public final void show(int width, int height, int move) {
        this.width = width;
        this.height = height;
        this.move = move;

        simpleWindow.create(width, height);
        simpleWindow.addContent(createContent());
    }

    private View createContent() {
        View content = LayoutInflater.from(context).inflate(R.layout.window_content, null);

        content.findViewById(R.id.move_up).setOnClickListener(this);
        content.findViewById(R.id.move_down).setOnClickListener(this);
        content.findViewById(R.id.move_left).setOnClickListener(this);
        content.findViewById(R.id.move_right).setOnClickListener(this);
        content.findViewById(R.id.close).setOnClickListener(this);

        return content;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.move_up:
                simpleWindow.move(0, -move);
                break;
            case R.id.move_down:
                simpleWindow.move(0, move);
                break;
            case R.id.move_left:
                simpleWindow.move(-move, 0);
                break;
            case R.id.move_right:
                simpleWindow.move(move, 0);
                break;
            case R.id.close:
                simpleWindow.destroy();
                break;
        }
    }
}
