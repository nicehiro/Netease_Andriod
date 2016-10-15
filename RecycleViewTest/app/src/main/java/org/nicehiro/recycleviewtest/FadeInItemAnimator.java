package org.nicehiro.recycleviewtest;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-10-15.
 */

public class FadeInItemAnimator extends SimpleItemAnimator {

    private List<RecyclerView.ViewHolder> pendingAddViewHolderList = new ArrayList<>();
    private List<RecyclerView.ViewHolder> addAnimationViewHolderList = new ArrayList<>();

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        View view = holder.itemView;
        ViewCompat.setAlpha(view, 0);
        pendingAddViewHolderList.add(holder);
        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    @Override
    public void runPendingAnimations() {
        for (int i = 0; i < pendingAddViewHolderList.size(); i++) {
            final RecyclerView.ViewHolder viewHolder = pendingAddViewHolderList.get(i);
            addAnimationViewHolderList.add(viewHolder);

            View view = viewHolder.itemView;
            final ViewPropertyAnimatorCompat animatorCompat = ViewCompat.animate(view);
        }
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
