package org.nicehiro.drawtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by root on 16-10-26.
 */

public class SelectorActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        findViewById(R.id.checkbox_disable_editor).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkbox_disable_editor) {
            boolean checked = ((CheckBox) v).isChecked();
            EditText editText = (EditText) findViewById(R.id.editor_shape_example);
            editText.setEnabled(!checked);
            editText.clearFocus();
        }
    }
}
