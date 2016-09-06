package com.com.zhihuichengdu.songwei.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by songwei on 2016/8/29.
 */
public class TopNewViewPager extends ViewPager {
    public TopNewViewPager(Context context) {
        super(context);
    }
    public TopNewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
