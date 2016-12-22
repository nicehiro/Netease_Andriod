package org.nicehiro.drawtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by root on 16-10-26.
 */

public class SrcAndScaleTypeActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;

    private static final int REQUEST_IMAGE_SELECT = 2;

    Uri content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_src_scale);

        imageView = (ImageView) findViewById(R.id.image_lenna);
        findViewById(R.id.get_intent).setOnClickListener(this);

//        setFromeResource();
//        setByDrawable();
        setFromPath();
//        setFromBitmap();

    }

    private void setFromBitmap() {
        String path = Environment.getExternalStorageDirectory().getPath();
        Bitmap bm = BitmapFactory.decodeFile(path + "tiger.jpg");
        imageView.setImageBitmap(bm);
    }

    private void setFromAlbum() {
        imageView.setImageURI(content);
    }

    private void setFromPath() {
        String path = Environment.getExternalStorageDirectory().getPath();
        Uri uri = Uri.fromFile(new File(path + "/tiger.jpg"));
        imageView.setImageURI(uri);
    }

    private void setByDrawable() {
        Drawable drawable = getDrawable(R.drawable.lenna);
        imageView.setImageDrawable(drawable);
    }

    private void setFromeResource() {
        imageView.setImageResource(R.drawable.lenna);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_intent:
                getImageIntent();
        }
    }

    public void getImageIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_SELECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_IMAGE_SELECT) {
            content = data.getData();
            setFromAlbum();
        }
    }
}
