package org.nicehiro.layouttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by root on 16-10-2.
 */

public class ScrollPic extends Activity{

    private LinearLayout gallary;
    private int[] imgs;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_pic);
        inflater = LayoutInflater.from(this);
        initImg();
        init();
    }

    private void initImg() {
        imgs = new int[] {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                /*R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.j,
                R.drawable.k,
                R.drawable.l,
                R.drawable.m,
                R.drawable.n,*/
        };
    }

    private void init() {

        gallary = (LinearLayout) findViewById(R.id.gallary);
        for (int i = 0; i < imgs.length; i++) {

            View view = inflater.inflate(R.layout.activity_index_gallery_item,// 找到布局文件
                    gallary, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);// 找到图片控件
            img.setImageResource(imgs[i]);
            img.setId(i);

            gallary.addView(view);

        }

    }
}
