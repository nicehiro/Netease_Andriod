package org.nicehiro.viewimp;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by hiro on 16-12-22.
 */

public class SamplePopupWindow implements View.OnClickListener {

    private Context context;

    private View anchor;

    private PopupWindow popupWindow;

    private int width = 0;

    private int height = 0;

    private int move = 0;

    private int moveX = 0;

    private int moveY = 0;

    public SamplePopupWindow(Context context, View anchor) {
        this.context = context;
        this.anchor = anchor;

        this.popupWindow = new PopupWindow(context);
    }

    public void show(int width, int height, int move) {
        this.width = width;
        this.height = height;
        this.move = move;

        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        popupWindow.setContentView(createContent());
        popupWindow.showAtLocation(anchor, Gravity.LEFT | Gravity.TOP, 0, 0);
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
                moveY -= 1;
                popupWindow.update(moveX*move, moveY*move, -1, -1);
                break;
            case R.id.move_down:
                moveY += 1;
                popupWindow.update(moveX*move, moveY*move, -1, -1);
                break;
            case R.id.move_left:
                moveX -= 1;
                popupWindow.update(moveX*move, moveY*move, -1, -1);
                break;
            case R.id.move_right:
                moveX += 1;
                popupWindow.update(moveX*move, moveY*move, -1, -1);
                break;
            case R.id.close:
                popupWindow.dismiss();
                break;
        }
    }
}
