package org.nicehiro.mybook;

import com.android.volley.VolleyError;

/**
 * Created by hiro on 17-3-21.
 */

public interface OnStringListener {

    void onSuccess(String result);

    void onError(VolleyError error);
}
