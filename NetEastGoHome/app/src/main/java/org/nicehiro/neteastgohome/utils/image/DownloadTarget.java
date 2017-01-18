package org.nicehiro.neteastgohome.utils.image;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by root on 1/18/17.
 */
public class DownloadTarget implements Target<File> {

    private Request mRequest = null;
    private ImageLoadListener mListener = null;
    private int mWidth = 0;
    private int mHeight = 0;

    public DownloadTarget(ImageLoadListener listener, int width, int height) {
        mListener = listener;
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        if (mListener != null) {
            mListener.onLoadStart();
        }
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        if (mListener != null) {
            mListener.onLoadFailed();
        }
    }

    @Override
    public void onResourceReady(File resource, GlideAnimation glideAnimation) {
        if (mListener != null) {
            mListener.onLoadSuccess(resource);
        }
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {}

    @Override
    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(mWidth, mHeight);
    }

    @Override
    public void setRequest(Request request) {
        mRequest = request;
    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
