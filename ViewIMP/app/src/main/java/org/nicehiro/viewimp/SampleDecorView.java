package org.nicehiro.viewimp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ViewSwitcher;

/**
 * Created by hiro on 16-12-22.
 */

public class SampleDecorView {
    private Activity activity;

    SampleDecorView(Activity activity) {
        this.activity = activity;
    }

    final void fg(boolean show) {
        FrameLayout decor = getDecorView(activity);

        if (decor == null) {
            return;
        }

        if (show) {
            Drawable drawable = activity.getResources().getDrawable(R.color.decor_foreground);
            decor.setForeground(drawable);
        } else {
            decor.setForeground(null);
        }
    }

    final void view(boolean add) {
        FrameLayout decor = getDecorView(activity);
        if (decor == null) {
            return;
        }

        if (add) {
            Drawable drawable = activity.getResources().getDrawable(R.color.decor_foreground);
            View view = new View(activity);
            view.setBackground(drawable);

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );

            layoutParams.bottomMargin = 50;
            layoutParams.topMargin = 50;
            layoutParams.leftMargin = 50;
            layoutParams.rightMargin = 50;

            decor.addView(view, layoutParams);
        } else {
            decor.removeViewAt(decor.getChildCount() - 1);
        }
    }

    private FrameLayout getDecorView(Activity activity) {
        View view = activity.getWindow().getDecorView();
        if (view instanceof FrameLayout) {
            return (FrameLayout) view;
        }
        return null;
    }
}
