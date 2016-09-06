package com.com.zhihuichengdu.songwei.page.menu;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.com.zhihuichengdu.songwei.bean.GetData;
import com.com.zhihuichengdu.songwei.page.BaseMenuDetailPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;
import com.zhihuichengdu.songwei.zhcd.MainActivity;
import com.zhihuichengdu.songwei.zhcd.R;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/25.
 * 使用 ViewPagerIndicator框架，首先页面布局添加相关代码
 * 在activity中通过findviewbyid 得到，并且  indicator.setViewPager(viewPager);方法必须在viewpager.setAdapter（）之后执行
 * 需要在manifest 文件中对相应的activity配置主题参数 android:theme="@style/Theme.PageIndicatorDefaults"
 * 可以根据需要，对PageIndicatorDefaults文件中的字体颜色等进行修改
 */
public class NewMenuDetailPager extends BaseMenuDetailPager {

    @ViewInject(R.id.new_menu_tab_viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.indicator)
    private TabPageIndicator indicator;
     @ViewInject(R.id.new_menu_detail_next)
     private ImageButton img_next;

    private ArrayList<GetData.Left_Menu> childern;
    private  ArrayList<TabPager> tabPagerArrayList;
    private  int count;
    public NewMenuDetailPager(Activity activity, ArrayList<GetData.Left_Menu> newsTabData) {
        super(activity);
        childern=newsTabData;
    }


    @Override
    public View init_view() {
       View view = View.inflate(mactivity, R.layout.news_menu_detail_tab,null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void init_data()
    {
        tabPagerArrayList = new ArrayList<TabPager>();

        for(int i=0;i<childern.size();i++)
        {
            TabPager tabPager = new TabPager(mactivity,childern.get(i));
             tabPagerArrayList.add(tabPager);
        }
        //System.out.println("-------------children size is -----:"+childern.size());
        //System.out.println("-------------tabPagerArrayList size is -----:"+tabPagerArrayList.size());
        viewPager.setAdapter(new NewsMenuDetailAdapter());
        indicator.setViewPager(viewPager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("current position is " + position);
                if (position == 0) {
                    EnableSlidingMenu(true);
                } else {
                    EnableSlidingMenu(false);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=viewPager.getCurrentItem();
                count=count+3;
                viewPager.setCurrentItem(count);
            }
        });
    }

public  class  NewsMenuDetailAdapter extends PagerAdapter
{

    @Override
    public CharSequence getPageTitle(int position) {
        return childern.get(position).title;
    }

    @Override
    public int getCount() {
       // Log.i("info", "tabPagerArrayList.size()=" + tabPagerArrayList.size());
        return tabPagerArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TabPager tabPager = tabPagerArrayList.get(position);
        View view =tabPager.init_view();
        container.addView(view);
        tabPager.init_data();
        return view;
    }
}
    private void EnableSlidingMenu(boolean b) {
        MainActivity main= (MainActivity)mactivity;
        SlidingMenu slidingMenu = main.getSlidingMenu();
        if(b){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else{
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }


}
