package com.com.zhihuichengdu.songwei.page.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.com.zhihuichengdu.songwei.bean.GetData;
import com.com.zhihuichengdu.songwei.page.BaseMenuDetailPager;
import com.com.zhihuichengdu.songwei.page.BasePage;
import com.com.zhihuichengdu.songwei.page.menu.InteractMenuDetailPager;
import com.com.zhihuichengdu.songwei.page.menu.NewMenuDetailPager;
import com.com.zhihuichengdu.songwei.page.menu.PhotoMenuDetailPager;
import com.com.zhihuichengdu.songwei.page.menu.ZhuanTiMenuDetailPager;
import com.com.zhihuichengdu.songwei.utils.Config;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhihuichengdu.songwei.zhcd.MainActivity;
import com.zhuhuichengdu.songwei.fragment.LeftMenuFragment;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/23.
 */
public class NewsPager extends BasePage {

    public MainActivity MainUI;
    private   GetData getData;
    private ArrayList<BaseMenuDetailPager> baseMenuDetailPagerArrayList;

    public NewsPager(Activity activity) {
        super(activity);
    }


    @Override
    public void init_data() {
        titleName.setText("新闻");

        /*if(UtilsCache.getData(mmActivity,Config.CATEGORIES_URL)!=null){
             ConvertJson(UtilsCache.getData(mmActivity,Config.CATEGORIES_URL));
        }
        else{
            HttpGet();
        }*/
        HttpGet();


    }

    private void HttpGet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, Config.CATEGORIES_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println("http url is :" + Config.CATEGORIES_URL);
                System.out.println("http get data is:" + result);
                ConvertJson(result);
                //UtilsCache.setData(mmActivity,Config.CATEGORIES_URL,result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }


    public void ConvertJson(String result) {
        Gson gson = new Gson();
         getData = gson.fromJson(result, GetData.class);
        System.out.println("convoert to json is:" + getData.toString());

        MainUI = (MainActivity) mmActivity;
        LeftMenuFragment leftMenuFragment = MainUI.getLeftMenuFragment();
        leftMenuFragment.setMenuData(getData.data);

        baseMenuDetailPagerArrayList = new ArrayList<BaseMenuDetailPager>();
        baseMenuDetailPagerArrayList.add(new NewMenuDetailPager(mmActivity,getData.data.get(0).children));
        baseMenuDetailPagerArrayList.add(new InteractMenuDetailPager(mmActivity));
        baseMenuDetailPagerArrayList.add(new ZhuanTiMenuDetailPager(mmActivity));
        baseMenuDetailPagerArrayList.add(new PhotoMenuDetailPager(mmActivity));
        setCurrentPager(0);
    }

    public void toggle() {
        SlidingMenu menu = MainUI.getSlidingMenu();
        menu.toggle();
    }
/*
将封装好的菜单详情界面放入集合中，通过不同的position 得到不同的界面
 */
    public void setCurrentPager(int position) {
        BaseMenuDetailPager baseMenuDetailPager = baseMenuDetailPagerArrayList.get(position);
        View view = baseMenuDetailPager.Root_view;
        main_fl_content.removeAllViews();                      //framelayout，重叠性
        main_fl_content.addView(view);

        baseMenuDetailPager.init_data();
        titleName.setText(getData.data.get(position).title);

    }
}
