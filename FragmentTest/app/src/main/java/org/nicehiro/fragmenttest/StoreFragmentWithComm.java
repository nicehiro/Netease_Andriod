package org.nicehiro.fragmenttest;

import android.app.Activity;
import android.view.View;

/**
 * Created by root on 16-10-10.
 */

public class StoreFragmentWithComm extends StoreFragmentWithState implements View.OnClickListener {
    private IComm comm;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof IComm)
            comm = (IComm) activity;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.store_view_count:
                notifyViewCount();
        }
    }

    private void notifyViewCount() {
        int viewCount = getViewCount();

        if (comm != null)
            comm.notifyViewCount(getTag(), viewCount);
    }
}
