package org.nicehiro.projectonetest;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.SurfaceView;

public class RenderPathActivity extends InfiniteFrameActivity {
    @Override
    protected InfiniteFramePlayer onCreatePlayer(SurfaceView surfaceView) {
        FrameRenderer renderer = new PathRenderer();

        HandlerThread thread = new HandlerThread("render");
        thread.start();
        PlayExecutor executor = new HandlerExecutor(new Handler(thread.getLooper()));

        InfiniteFramePlayer player = new InfiniteFramePlayer(surfaceView.getHolder(), renderer, executor);

        surfaceView.setOnTouchListener(new TouchPathFrameSource(player.getFrameSink()));

        return player;
    }
}
