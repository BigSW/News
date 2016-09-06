package com.com.zhihuichengdu.songwei.page;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zhihuichengdu.songwei.zhcd.MainActivity;
import com.zhihuichengdu.songwei.zhcd.R;

/**
 * Created by songwei on 2016/8/23.
 */
public class BasePage {
    public Activity mmActivity;
    public View mRootView;
    public TextView titleName;
    public ImageButton title_image;
    public FrameLayout main_fl_content;


    public BasePage(Activity activity) {
        mmActivity = activity;
        mRootView = init_view();
    }


    public View init_view() {
        View view = View.inflate(mmActivity, R.layout.main_page, null);
        titleName = (TextView) view.findViewById(R.id.title_name);
        title_image = (ImageButton) view.findViewById(R.id.title_image);
        main_fl_content = (FrameLayout) view.findViewById(R.id.main_fl_content);

        title_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toogle();
            }


        });
        return view;

    }

    private void toogle() {
        MainActivity main= (MainActivity) mmActivity;
        main.getSlidingMenu().toggle();
    }


    public void init_data() {

    }


}
