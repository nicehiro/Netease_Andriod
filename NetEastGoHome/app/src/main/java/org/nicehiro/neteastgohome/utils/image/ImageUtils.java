package org.nicehiro.neteastgohome.utils.image;

import android.content.Context;
import android.util.SparseArray;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.io.File;

/**
 * Created by hiro on 1/14/17.
 */

public class ImageUtils {

    private static MemoryCache sMemoryCache = null;
    private static GlideCircleTransform sCircleTransform = null;
    private static SparseArray<GlideRoundTransform> sRoundTransforms = new SparseArray<>();

    /*package*/
    static MemoryCache getsMemoryCache(Context context) {
        if (sMemoryCache == null) {
            synchronized (ImageUtils.class) {
                if (sMemoryCache == null) {
                    MemorySizeCalculator calculator = new MemorySizeCalculator(context);
                    sMemoryCache = new LruResourceCache(calculator.getMemoryCacheSize());
                }
            }
        }

        return sMemoryCache;
    }

    private static BitmapTransformation getCircleBitmapTansform(Context context) {
        if (sCircleTransform == null) {
            synchronized (ImageUtils.class) {
                if (sCircleTransform == null) {
                    sCircleTransform = new GlideCircleTransform(context);
                }
            }
        }

        return sCircleTransform;
    }

    private static synchronized BitmapTransformation getRoundBitmapTransform(Context context, int radiusDp) {
        GlideRoundTransform transform = sRoundTransforms.get(radiusDp);
        if (transform == null) {
            transform = new GlideRoundTransform(context, radiusDp);
            sRoundTransforms.put(radiusDp, transform);
        }

        return transform;
    }

    // 针对网络图片
    public static void setUrl(ImageView imageView, String url) {
        setUrl(imageView, url, 0, 0, null);
    }

    public static void setUrl(ImageView imageView, String url, int width, int height) {
        setUrl(imageView, url, width, height, null);
    }

    public static void setUrl(ImageView imageView, String url, int radiusDp) {
        BitmapTransformation transformation = getRoundBitmapTransform(imageView.getContext(), radiusDp);
        setUrl(imageView, url, 0, 0, transformation);
    }

    public static void setUrl(ImageView imageView, String url, int width, int height, int radiusDp) {
        BitmapTransformation transformation = getRoundBitmapTransform(imageView.getContext(), radiusDp);
        setUrl(imageView, url, width, height, transformation);
    }

    public static void setCircleUrl(ImageView imageView, String url) {
        BitmapTransformation transformation = getCircleBitmapTansform(imageView.getContext());
        setUrl(imageView, url, 0, 0, transformation);
    }

    public static void setCirclrUrl(ImageView imageView, String url, int width, int height) {
        BitmapTransformation transformation = getCircleBitmapTansform(imageView.getContext());
        setUrl(imageView, url, width, height, transformation);
    }

    public static void setUrl(ImageView imageView, String url, int width, int height, BitmapTransformation transformation) {
        String sizeUrl = (width > 0 && height > 0) ?
                UrlGenerator.getImgUrl(url, width, height) :
                url;

        DrawableRequestBuilder<String> builder = Glide.with(imageView.getContext())
                .load(sizeUrl)
                .centerCrop()
                .animate(android.R.anim.fade_in);
        if (transformation != null) {
            builder.bitmapTransform(transformation);
        }
        builder.into(imageView);
    }

    // 针对本地资源图片
    public static void setResId(ImageView imageView, int resId) {
        setResId(imageView, resId, null);
    }

    public static void setResId(ImageView imageView, int resId, int radiusDp) {
        BitmapTransformation transformation = getRoundBitmapTransform(imageView.getContext(), radiusDp);
        setResId(imageView, resId, transformation);
    }

    public static void setCircleResId(ImageView imageView, int resId) {
        BitmapTransformation transformation = getCircleBitmapTansform(imageView.getContext());
        setResId(imageView, resId, transformation);
    }

    public static void setResId(ImageView imageView, int resId, BitmapTransformation transformation) {
        DrawableRequestBuilder<Integer> builder = Glide.with(imageView.getContext())
                .load(resId)
                .centerCrop()
                .animate(android.R.anim.fade_in);
        if (transformation != null) {
            builder.bitmapTransform(transformation);
        }
        builder.into(imageView);
    }

    //针对 sdcard 图片
    public static void setFilePath(ImageView imageView, String filePath) {
        setFilePath(imageView, filePath, null);
    }

    public static void setFilePah(ImageView imageView, String filePath, int radiusDp) {
        BitmapTransformation transformation = getRoundBitmapTransform(imageView.getContext(), radiusDp);
        setFilePath(imageView, filePath, transformation);
    }

    public static void setCircleFilePath(ImageView imageView, String filePath) {
        BitmapTransformation transformation = getCircleBitmapTansform(imageView.getContext());
        setFilePath(imageView, filePath, transformation);
    }

    public static void setFilePath(ImageView imageView, String filePath, BitmapTransformation transformation) {
        DrawableRequestBuilder<String> builder = Glide.with(imageView.getContext())
                .load(filePath)
                .centerCrop()
                .animate(android.R.anim.fade_in);
        if (transformation != null) {
            builder.bitmapTransform(transformation);
        }
        builder.into(imageView);
    }

    public static long getDiskCacheSize(Context context) {
        File diskCacheDir = getDiskCacheDir(context);
        return getFolderSize(diskCacheDir);
    }

    public static void clearDiskCache(final Context context, final ClearDiskCacheListener listener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
                if (listener != null) {
                    listener.onDiskCacheListener();
                }
            }
        });

        thread.start();
    }

    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    public static void prefetch(Context context, String url, int width, int height) {
        String sizeUrl = (width > 0 && height > 0) ?
                UrlGenerator.getImgUrl(url, width, height) :
                url;
        Glide.with(context).load(sizeUrl).downloadOnly(width, height);
    }

    public static void prefetch(Context context, String url, int width, int height, ImageLoadListener listener) {
        String sizeUrl = (width > 0 && height > 0) ?
                UrlGenerator.getImgUrl(url, width, height) :
                url;
        Glide.with(context).load(sizeUrl).downloadOnly(new DownloadTarget(listener, width, height));
    }

    private static File getDiskCacheDir(Context context) {
        File diskCacheDir = context.getCacheDir();
        return new File(diskCacheDir, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR);
    }

    private static long getFolderSize(File file) {
        long size = 0;
        try {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                for (File subFile : fileList) {
                    if (subFile.isDirectory()) {
                        size += getFolderSize(subFile);
                    } else {
                        size += subFile.length();
                    }
                }
            } else {
                size += file.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }
}
