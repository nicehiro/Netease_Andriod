package org.nicehiro.viewpagertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by root on 16-10-17.
 */

public class ColorFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private String colorName;
    private int colorValue;

    private void initArgs() {
        Bundle args = getArguments();

        if (args != null) {
            colorName = args.getString("colorName");
            colorValue = args.getInt("colorValue");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initArgs();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.color_name).setOnClickListener(this);
        setupPage(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.color_name:
                Toast.makeText(v.getContext(), "color: " + colorName, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setupPage(View view) {
        view.setBackgroundColor(colorValue);
    }
}
