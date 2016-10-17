package org.nicehiro.viewpagertest;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by root on 16-10-17.
 */

public class ColorPagerAdapter extends PagerAdapter{

    protected final IColorData colorData;
    private final Context context;

    public ColorPagerAdapter(IColorData colorData, Context context) {
        this.colorData = colorData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return colorData.getCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_color, container, false);

        container.addView(view);

        setupPage(view, position);

        Toast.makeText(context, "instantiateitem " + position, Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Toast.makeText(context, "destroyItem " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return colorData.getColorName(position);
    }

    /*
    @Override
    public float getPageWidth(int position) {
        return 0.5f + 0.1f * position;
    }
*/

    private void setupPage(View view, int position) {
        view.setBackgroundColor(colorData.getColorValue(position));
        TextView textView = (TextView) view.findViewById(R.id.color_name);
        textView.setText(colorData.getColorName(position));
    }
}
