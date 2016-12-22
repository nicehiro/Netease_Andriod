## 实现一个水平滚动的线性布局
做了两个测试，第一个是自己做第二个参考网上做的。

### 简单水平滚动
.xml file
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="20dp"
    android:paddingRight="20dp"
    android:paddingTop="50dp"
    android:paddingBottom="50dp"
    android:background="@color/grey">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/introduce"
        android:textSize="24dp"
        android:gravity="center"
        android:background="@color/origen"
        android:textColor="@color/white"/>

    <HorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="400dp"
        android:scrollbars="none"
        android:layout_marginTop="100dp">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent">

            <TextView
                android:id="@+id/next"
                android:text="@string/task1"
                android:textSize="24dp"
                android:textColor="@color/white"
                android:gravity="center"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:padding="10dp"
                android:layout_height="match_parent"
                android:background="@color/colorAccent" />
            <TextView
                android:id="@+id/show_call_activity"
                android:text="@string/task2"
                android:textSize="24dp"
                android:textColor="@color/white"
                android:gravity="center"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:padding="10dp"
                android:layout_height="match_parent"
                android:background="@color/blue" />
            <TextView
                android:id="@+id/nothing"
                android:text="@string/task3"
                android:textSize="24dp"
                android:textColor="@color/white"
                android:gravity="center"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:padding="10dp"
                android:layout_height="match_parent"
                android:background="@color/green"/>
        </LinearLayout>
    </HorizontalScrollView>

</RelativeLayout>
```
效果图：
![](http://oeoaak94a.bkt.clouddn.com/test1.png)
### 水平滚动图片
.xml file
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="20dp"
    android:paddingRight="20dp"
    android:paddingTop="50dp"
    android:paddingBottom="50dp">

    <TextView
        android:autoLink="all"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="refer to hongyang's article:                 http://blog.csdn.net/lmj623565791/article/details/38140505"
        android:textSize="20sp"/>

    <HorizontalScrollView
        android:layout_width="wrap_content"
        android:layout_height="150dp"
        android:layout_centerVertical="true"
        android:scrollbars="none">

        <LinearLayout
            android:id="@+id/gallary"
            android:orientation="horizontal"
            android:layout_width="wrap_content"
            android:layout_height="match_parent">

        </LinearLayout>
    </HorizontalScrollView>

</RelativeLayout>
```

.java file
```java
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
```
效果图如下：
![](http://oeoaak94a.bkt.clouddn.com/Screenshot_20161008-153047.png)