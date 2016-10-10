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

public class MallActivityWithTrans extends Activity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_store:
                addStore();
                break;
            case R.id.change_store:
                changeStore();
        }
    }

    private void changeStore() {
        Bundle argsToReplace = new Bundle();
        argsToReplace.putString("name", "replace");

        Fragment fragmentToReplace = new StoreFragmentWithState();
        fragmentToReplace.setArguments(argsToReplace);

        Bundle argsToAdd = new Bundle();
        argsToAdd.putString("name", "add2");
        Fragment fragmentToAdd = new StoreFragmentWithState();
        fragmentToAdd.setArguments(argsToAdd);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.store_container1, fragmentToReplace, "replace");
        fragmentTransaction.replace(R.id.store_container2, fragmentToAdd, "add2");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addStore() {
        Bundle argsToAdd = new Bundle();
        argsToAdd.putString("name", "store with name");

        Fragment fragment = new StoreFragmentWithState();
        fragment.setArguments(argsToAdd);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.store_container2, fragment, "add");
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_with_trans);

        findViewById(R.id.add_store).setOnClickListener(this);
        findViewById(R.id.change_store).setOnClickListener(this);
    }
}
