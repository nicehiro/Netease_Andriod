package org.nicehiro.mybook;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hiro on 17-3-17.
 */

public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getVolleySingleton(Context context) {
        if (volleySingleton == null) {
            return new VolleySingleton(context);
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
