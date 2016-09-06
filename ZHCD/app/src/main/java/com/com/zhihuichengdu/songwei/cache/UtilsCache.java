package com.com.zhihuichengdu.songwei.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by songwei on 2016/8/25.
 */
public class UtilsCache {
    public static void setData(Context context,String url,String json){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putString(url,json).commit();
    }
    public static String getData(Context context,String url){
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
       return sp.getString(url,null);
    }
}
