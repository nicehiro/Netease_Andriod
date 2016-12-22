package org.nicehiro.projectonetest;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class FramePlayer {
    private static final String TAG = "FramePlayer";

    private static final int DEFAULT_INTERVAL = 40;

    public enum State {
        STOPPED,
        PLAYING,
        PAUSED,
    }

    public interface Callback {
        void onState(State state);
    }

    // surface holder
    private final SurfaceHolder surfaceHolder;

    // frame render
    private final FrameRenderer renderer;

    // play executor
    private final PlayExecutor executor;

    // frame manager
    private final FrameManager manager = new FrameManager();

    // play state
    private State state = State.STOPPED;

    // play interval
    private int interval = DEFAULT_INTERVAL;

    // callback
    private Callback callback;

    public FramePlayer(SurfaceHolder surfaceHolder, FrameRenderer renderer, PlayExecutor executor) {
        this.surfaceHolder = surfaceHolder;
        this.renderer = renderer;
        this.executor = executor;

        executor.setRunner(new Runnable() {
            @Override
            public void run() {
                renderFrame();
            }
        });

        initSurfaceHolder();
    }

    private void initSurfaceHolder() {
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(TAG, "surfaceChanged:"
                        + " format " + format
                        + " width " + width
                        + " height " + height
                );

                stop();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed");

                stop();
            }
        });
    }

    public final void setFPS(int fps) {
        if (fps > 0) {
            interval = 1000 / fps;
        }
    }

    public final void setCallback(Callback callback) {
        this.callback = callback;
    }

    public final boolean play() {
        if (state == State.PLAYING) {
            return false;
        }
        changeState(State.PLAYING);
        executor.requestNow();
        return true;
    }

    public final boolean pause() {
        if (state != State.PLAYING) {
            return false;
        }
        changeState(State.PAUSED);
        executor.drain();
        return true;
    }

    public final void stop() {
        changeState(State.STOPPED);
        executor.drain();
        manager.drain();
    }

    protected final void produce(Frame frame) {
        produce(frame, true);
    }

    protected final void produce(Frame frame, boolean requestNow) {
        if (manager.produce(frame)) {
            if (requestNow) {
                executor.requestNow();
            }
        }
    }

    protected final State getState() {
        return state;
    }

    protected Frame produce() {
        return null;
    }

    protected boolean mayProduce() {
        return false;
    }

    protected void onState(State state) {

    }

    private void changeState(State state) {
        this.state = state;
        onState(state);
        if (callback != null) {
            callback.onState(state);
        }
    }

    private void renderFrame() {
        Frame frame = manager.consume();
        if (frame == null) {
            frame = produce();
        }

        if (frame != null) {
            renderFrame(frame);
        }

        if (!manager.isEmpty() || mayProduce()) {
            executor.requestDelayed(interval);
        }
    }

    private void renderFrame(Frame frame) {
        Canvas canvas = surfaceHolder.lockCanvas(null);
        if (canvas == null) {
            return;
        }

        renderer.render(canvas, frame);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}