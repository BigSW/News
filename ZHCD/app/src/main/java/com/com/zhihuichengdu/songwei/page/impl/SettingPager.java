package com.com.zhihuichengdu.songwei.page.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.com.zhihuichengdu.songwei.page.BasePage;

/**
 * Created by songwei on 2016/8/23.
 */
public class SettingPager extends BasePage {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void init_data() {
        TextView textView = new TextView(mmActivity);
        textView.setText("SheZhi");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        main_fl_content.addView(textView);
        titleName.setText("设置");
    }

    @Override
    public View init_view() {
        return super.init_view();
    }
}
