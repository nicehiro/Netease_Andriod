package org.nicehiro.projectonetest;

import java.util.LinkedList;
import java.util.Queue;

public class FrameManager {
    // pending frames
    private final Queue<Frame> frames = new LinkedList<Frame>();

    public final boolean produce(Frame frame) {
        frames.add(frame);

        return true;
    }

    public final Frame consume() {
        if (!frames.isEmpty()) {
            return frames.poll();
        }

        return null;
    }

    public final void drain() {
        frames.clear();
    }

    public final boolean isEmpty() {
        return frames.isEmpty();
    }
}
