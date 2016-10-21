package org.nicehiro.title;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by root on 16-10-21.
 */

public class PlusProvider extends ActionProvider {
    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public PlusProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        subMenu.addSubMenu("tab1").setIcon(R.drawable.logo);
        subMenu.addSubMenu("tab2").setIcon(R.drawable.logo);
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
