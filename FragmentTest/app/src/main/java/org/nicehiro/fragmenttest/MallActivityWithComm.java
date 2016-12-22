package org.nicehiro.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by root on 16-10-10.
 */

public class MallActivityWithComm extends Activity implements View.OnClickListener, IComm{

    private TextView tvCommText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_with_comm);

        findViewById(R.id.add_stores).setOnClickListener(this);
        findViewById(R.id.change_stores).setOnClickListener(this);
        tvCommText = (TextView) findViewById(R.id.comm_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_stores:
                addStore();
                break;
            case R.id.change_stores:
                changeStores();
                break;
        }
    }

    private void addStore() {
        Bundle args = new Bundle();
        args.putString("name", "store with name");

        Fragment fragment = new StoreFragmentWithComm();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.store_container2, fragment, "add");
        fragmentTransaction.commit();
    }

    private void changeStores() {
        Bundle argsToReplace = new Bundle();
        argsToReplace.putString("name", "replace");

        Fragment fragmentToReplace = new StoreFragmentWithComm();

        fragmentToReplace.setArguments(argsToReplace);

        Bundle argsToAdd = new Bundle();
        argsToAdd.putString("name", "add2");

        Fragment fragmentToAdd = new StoreFragmentWithComm();

        fragmentToAdd.setArguments(argsToAdd);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.store_container1, fragmentToReplace, "replace");
        fragmentTransaction.add(R.id.store_container2, fragmentToAdd, "add2");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void notifyViewCount(String tag, int viewCount) {
        String message = "recive view count: " + tag + " " + viewCount;
        tvCommText.setText(message);
    }
}
