package org.nicehiro.projectonetest;

import android.view.SurfaceHolder;

public class InfiniteFramePlayer extends FramePlayer {
    public InfiniteFramePlayer(SurfaceHolder surfaceHolder, FrameRenderer renderer, PlayExecutor executor) {
        super(surfaceHolder, renderer, executor);
    }

    public final FrameSink getFrameSink() {
        return new FrameSink() {
            @Override
            public void onFrame(Frame frame) {
                if (!acceptFrame()) {
                    return;
                }

                produce(frame, false);
            }
        };
    }

    @Override
    protected final boolean mayProduce() {
        return true;
    }

    private boolean acceptFrame() {
        return getState() != State.STOPPED;
    }
}