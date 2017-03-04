package org.nicehiro.neteastgohome.share;

/**
 * Created by root on 17-2-10.
 */

import android.support.annotation.NonNull;

public interface ShareApi {
    enum shareType {
        SYSTEM,
        WEIBO,
        WEIXIN,
    }

    void share(@NonNull String content, @NonNull String imagePath);
}
