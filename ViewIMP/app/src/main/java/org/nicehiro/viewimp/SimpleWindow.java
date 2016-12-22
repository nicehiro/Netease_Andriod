package org.nicehiro.viewimp;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ViewSwitcher;

/**
 * Created by hiro on 16-12-22.
 */

public class SimpleWindow {
    private Context context;

    private WindowManager windowManager;

    private ViewGroup decor;

    public SimpleWindow(Context context) {
        this.context = context;
        this.windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public final void create(int width, int height) {
        decor = new DecorView(context);
        windowManager.addView(decor, createWindowLayoutParams(width, height));
    }

    private LayoutParams createWindowLayoutParams(int width, int height) {
        WindowManager.LayoutParams layoutParams = new LayoutParams();

        layoutParams.type = LayoutParams.TYPE_APPLICATION_PANEL;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.START | Gravity.TOP;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = width;
        layoutParams.height = height;

        return layoutParams;
    }

    public final void addContent(View content) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        decor.addView(content, layoutParams);
    }

    public final void move(int moveX, int moveY) {
        WindowManager.LayoutParams layoutParams = (LayoutParams) decor.getLayoutParams();
        adjustWindowLayoutParams(layoutParams, moveX, moveY);
        windowManager.updateViewLayout(decor, layoutParams);
    }

    public final void destroy() {
        windowManager.removeView(decor);
    }

    private static void adjustWindowLayoutParams(LayoutParams layoutParams, int moveX, int moveY) {
        layoutParams.x += moveX;
        layoutParams.y += moveY;
    }

    private static class DecorView extends FrameLayout {
        public DecorView(Context context) {
            super(context);
        }
    }
}
