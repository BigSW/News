package com.com.zhihuichengdu.songwei.page.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.com.zhihuichengdu.songwei.page.BasePage;

/**
 * Created by songwei on 2016/8/23.
 */
public class SerPager extends BasePage {
    public SerPager(Activity activity) {
        super(activity);
    }

    @Override
    public void init_data() {
        TextView textView = new TextView(mmActivity);
        textView.setText("Ser");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);

        main_fl_content.addView(textView);
        titleName.setText("智慧服务");
        Log.i(">>>>>>>>", "init_data: "+titleName.getText());
    }

    @Override
    public View init_view() {
        return super.init_view();
    }
}
