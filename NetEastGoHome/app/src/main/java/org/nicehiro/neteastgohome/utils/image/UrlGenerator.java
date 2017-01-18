package org.nicehiro.neteastgohome.utils.image;

import java.util.Locale;

/**
 * Created by root on 1/18/17.
 */

public class UrlGenerator {

    public static final String sSizeFormat = "%s?imageView&View&thumbnail=%dx%d";

    public static String getImgUrl(String url, int width, int height) {
        return String.format(Locale.getDefault(), sSizeFormat, url, width, height);
    }
}
