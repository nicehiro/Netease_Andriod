package org.nicehiro.neteastgohome.utils.image;

import java.io.File;

/**
 * Created by root on 1/18/17.
 */
public interface ImageLoadListener {
    void onLoadSuccess(File resource);
    void onLoadFailed();
    void onLoadStart();
}
