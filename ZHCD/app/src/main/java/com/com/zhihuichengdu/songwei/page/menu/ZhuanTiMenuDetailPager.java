package com.com.zhihuichengdu.songwei.page.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.com.zhihuichengdu.songwei.page.BaseMenuDetailPager;

/**
 * Created by songwei on 2016/8/25.
 */
public class ZhuanTiMenuDetailPager extends BaseMenuDetailPager {
    public ZhuanTiMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View init_view() {
        TextView textView = new TextView(mactivity);
        textView.setText("menu-Zhuanti");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
}
