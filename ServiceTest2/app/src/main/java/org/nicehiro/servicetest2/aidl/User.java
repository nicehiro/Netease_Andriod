package org.nicehiro.servicetest2.aidl;

import java.io.Serializable;

/**
 * Created by hiro on 16-12-9.
 */

public class User implements Serializable {
    private static final long serialVersionID = 7683497873112866208L;

    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public User(String name) {
        mName = name;
    }
}
