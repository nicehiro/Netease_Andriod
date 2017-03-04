package org.nicehiro.neteastgohome.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.nicehiro.neteastgohome.R;

import java.io.File;

/**
 * Created by root on 17-2-10.
 */

public class SystemShare extends AbstructShareAPI {
    public SystemShare(@NonNull Activity context) {
        super(context);
    }

    @Override
    public void share(@NonNull String content, @NonNull String imagePath) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        if (!TextUtils.isEmpty(content)) {
            intent.putExtra(Intent.EXTRA_TEXT, content);
            intent.setType("text/plain");
        }

        if (!TextUtils.isEmpty(imagePath)) {
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imagePath)));
            intent.setType("image/*");
        }
        mContext.startActivity(Intent.createChooser(intent, mContext.getString(R.string.share_title)));
    }
}
