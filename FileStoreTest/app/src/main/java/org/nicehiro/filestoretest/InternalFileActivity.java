package org.nicehiro.filestoretest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by root on 16-11-8.
 */

public class InternalFileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private EditText userPw;

    private CheckBox remember;

    // you have to new a file to save the state of isSelected
    private boolean isSelected = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login).setOnClickListener(this);

        remember = (CheckBox) findViewById(R.id.remember);

        userName = (EditText) findViewById(R.id.user_name);
        userPw = (EditText) findViewById(R.id.user_pw);

        readFile(this);
    }

    private void saveFile() {
        String userName = this.userName.getText().toString();
        String userPw = this.userPw.getText().toString();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPw)) {
            Toast.makeText(this, "UserName or UserPwd cannot be null", Toast.LENGTH_SHORT).show();
        } else if (remember.isSelected()){
            isSelected = true;
        }

        if (isSelected) {
            saveFile(this, userName, userPw);
        }
    }

    private void saveFile(Context context, String userName, String pwd) {

        FileOutputStream outputStream = null;

        try {
            outputStream = context.openFileOutput("myuser", Context.MODE_PRIVATE);
            String userPw = String.format("%s;%s", userName, pwd);
            byte[] bytes = userPw.getBytes("utf-8");
            outputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void readFile(Context context) {
        InputStream inputStream = null;
        String strUserPw = null;

        try {
            inputStream = context.openFileInput("myuser");
            strUserPw = Util.getStringStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!TextUtils.isEmpty(strUserPw)) {
            String[] namePw = strUserPw.split(";");
            userName.setText(namePw[0]);
            userPw.setText(namePw[1]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                saveFile();
                break;
        }
    }
}
