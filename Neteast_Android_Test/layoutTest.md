# 实现总结页面的布局

我是把老师文件的图片直接贴过来的，然后不知道为什么图片尺寸就发生了变化，详情请看图。

.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="org.nicehiro.layouttest.MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:paddingBottom="20dp">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <TextView
                android:id="@+id/input_number"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="12"
                android:textColor="@color/black"
                android:textSize="20sp"
                android:layout_centerInParent="true"
                android:padding="10dp"/>
            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentRight="true"
                android:layout_marginRight="20dp"
                android:layout_marginBottom="40dp"
                android:layout_centerVertical="true"
                android:src="@drawable/phone_keyboard_backspace_normal"/>
        </RelativeLayout>

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="20dp">

            <TextView
                android:id="@+id/name"
                android:layout_marginLeft="10dp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Hiro"
                android:textSize="20sp"
                android:textColor="@color/black"/>
            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/active_small"
                android:layout_toRightOf="@+id/name"
                android:layout_alignTop="@id/name"
                android:layout_alignBottom="@id/name"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_below="@+id/name"
                android:text="123456789"
                android:textSize="16sp"/>
            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentRight="true"
                android:layout_centerVertical="true"
                android:src="@drawable/phone_keyboard_down_normal"/>
        </RelativeLayout>
    </LinearLayout>

    <TableLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:stretchColumns="*">

        <TableRow>
            <TextView
                style="@style/number_style"
                android:text="1"/>
            <TextView
                style="@style/number_style"
                android:text="2"/>
            <TextView
                style="@style/number_style"
                android:text="3"/>
        </TableRow>

        <TableRow>
            <TextView
                style="@style/number_style"
                android:text="4"/>
            <TextView
                style="@style/number_style"
                android:text="5"/>
            <TextView
                style="@style/number_style"
                android:text="6"/>
        </TableRow>

        <TableRow>
            <TextView
                style="@style/number_style"
                android:text="7"/>
            <TextView
                style="@style/number_style"
                android:text="8"/>
            <TextView
                style="@style/number_style"
                android:text="9"/>
        </TableRow>

        <TableRow>
            <TextView
                style="@style/number_style"
                android:text="0"
                android:layout_column="1"/>
        </TableRow>

        <ImageView
            android:src="@drawable/phone_keyboard_call_normal"
            android:paddingTop="10dp"/>
    </TableLayout>

</FrameLayout>
```
效果图如下：
![](http://oeoaak94a.bkt.clouddn.com/Screenshot_20161008-154132.png)