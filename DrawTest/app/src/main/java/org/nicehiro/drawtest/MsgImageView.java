package org.nicehiro.drawtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by root on 16-10-27.
 */

public class MsgImageView extends ImageView {

    private Paint paint = null;

    public MsgImageView(Context context) {
        super(context);
    }

    public MsgImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MsgImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MsgImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        int saveCount = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);

        Drawable background = getContext().getResources().getDrawable(R.drawable.msg_background);
        background.setBounds(0, 0, width, height);
        background.draw(canvas);

        canvas.saveLayer(0, 0, width, height, backPaint(), Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        canvas.restore();

        canvas.restoreToCount(saveCount);
    }

    private Paint backPaint() {
        if (paint == null) {
            paint = new Paint();
            PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
            paint.setXfermode(xfermode);
        }
        return paint;
    }
}
