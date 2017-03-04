package org.nicehiro.neteastgohome.share;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by root on 17-2-10.
 */

public abstract class AbstructShareAPI implements ShareApi {
    protected Activity mContext;

    public AbstructShareAPI(@NonNull Activity context) {
        mContext = context;
    }
}
