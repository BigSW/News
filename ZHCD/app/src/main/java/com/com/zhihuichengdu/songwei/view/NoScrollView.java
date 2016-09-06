package com.com.zhihuichengdu.songwei.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by songwei on 2016/8/23.
 */
public class NoScrollView extends ViewPager {
    public NoScrollView(Context context) {
        super(context);
    }
    public NoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return  false;

    }
}
