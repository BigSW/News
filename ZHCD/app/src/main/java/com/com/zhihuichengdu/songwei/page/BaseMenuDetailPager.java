package com.com.zhihuichengdu.songwei.page;

import android.app.Activity;
import android.view.View;

import com.zhuhuichengdu.songwei.fragment.BaseFragment;

/**
 * Created by songwei on 2016/8/25.
 */
public abstract class BaseMenuDetailPager {

    public View Root_view;
    public Activity mactivity;

    public BaseMenuDetailPager(Activity activity){
        mactivity=activity;
        Root_view = init_view();
    }
    public  void init_data(){}
    public abstract View init_view();
}
