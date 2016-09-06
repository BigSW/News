package com.com.zhihuichengdu.songwei.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by songwei on 2016/8/30.
 */
public class demoa extends View {
    public demoa(Context context) {
        super(context);
    }

    public demoa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public demoa(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public demoa(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
