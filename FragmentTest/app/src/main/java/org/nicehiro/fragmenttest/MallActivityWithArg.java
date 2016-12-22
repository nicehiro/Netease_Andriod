package org.nicehiro.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

/**
 * Created by root on 16-10-9.
 */

public class MallActivityWithArg extends Activity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_store:
                addStore();
        }
    }

    private void addStore() {
        Bundle args = new Bundle();
        args.putString("name", "store with name");

        Fragment fragment = new StoreFragmentWithArg();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.store_container2, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_with_arg);

        findViewById(R.id.add_store).setOnClickListener(this);
    }
}
