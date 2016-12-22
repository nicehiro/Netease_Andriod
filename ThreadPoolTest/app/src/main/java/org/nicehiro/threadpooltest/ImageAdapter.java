package org.nicehiro.threadpooltest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by hiro on 16-11-21.
 */

public class ImageAdapter extends BaseAdapter {

    private MainActivity mainActivity;
    private LayoutInflater inflater;

    public ImageAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.inflater = mainActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_image_view, null);
            ImageView imageView0 = (ImageView) convertView.findViewById(R.id.image_0);
            ImageView imageView1 = (ImageView) convertView.findViewById(R.id.image_1);
            ImageView imageView2 = (ImageView) convertView.findViewById(R.id.image_2);

            setLayoutParam(imageView0);
            setLayoutParam(imageView1);
            setLayoutParam(imageView2);

            convertView.setTag(new ViewHolder(imageView0, imageView1, imageView2));
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        mainActivity.mThreadPool.execute(new ImageTask(viewHolder.iv0, mainActivity, position, 0));
        mainActivity.mThreadPool.execute(new ImageTask(viewHolder.iv1, mainActivity, position, 0));
        mainActivity.mThreadPool.execute(new ImageTask(viewHolder.iv2, mainActivity, position, 0));

        return convertView;
    }

    private static class ViewHolder {
        ImageView iv0;
        ImageView iv1;
        ImageView iv2;

        public ViewHolder(ImageView iv0, ImageView iv1, ImageView iv2) {
            this.iv0 = iv0;
            this.iv1 = iv1;
            this.iv2 = iv2;
        }
    }

    private void setLayoutParam(ImageView imageView) {
        DisplayMetrics dm = mainActivity.getApplicationContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = lp.height = screenWidth / 3;
    }

    static HashMap<String, Bitmap> sImageCache = new HashMap<>();

    public static class ImageTask implements Runnable {

        private ImageView mImageView;
        private SoftReference<Activity> mActivityRef;
        private int mPosition = 0;
        private int mNumber = 0;

        public ImageTask(ImageView mImageView, Activity activity,
                         int mPosition, int mNumber) {
            mImageView.setImageBitmap(null);
            this.mImageView = mImageView;
            this.mPosition = mPosition;
            this.mActivityRef = new SoftReference<Activity>(activity);
            this.mNumber = mNumber;
        }

        @Override
        public void run() {
            for (int i=0; i<10000; i++) {
                for (int j=0; j<10000; j++) {
                    //TODO
                }
            }

            Activity activity = mActivityRef.get();

            if (activity != null) {
                final String name = "image" + ((mPosition * 3 + mNumber) % 7) + ".jpg";
                if (!sImageCache.containsKey(name)) {
                    try {
                        InputStream is = activity.getAssets().open(name);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        sImageCache.put(name, bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = sImageCache.get(name);
                        setImageBitmap(bitmap);
                    }
                });
            }
        }

        private void setImageBitmap(Bitmap bitmap) {
            if (mImageView != null && bitmap != null && !bitmap.isRecycled()) {
                mImageView.setImageBitmap(bitmap);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(300);
                mImageView.setAnimation(alphaAnimation);
                alphaAnimation.startNow();
            }
        }
    }
}
