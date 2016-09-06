package com.zhuhuichengdu.songwei.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.com.zhihuichengdu.songwei.page.BasePage;
import com.com.zhihuichengdu.songwei.page.impl.GovPager;
import com.com.zhihuichengdu.songwei.page.impl.HomePager;
import com.com.zhihuichengdu.songwei.page.impl.NewsPager;
import com.com.zhihuichengdu.songwei.page.impl.SerPager;
import com.com.zhihuichengdu.songwei.page.impl.SettingPager;
import com.com.zhihuichengdu.songwei.view.NoScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zhihuichengdu.songwei.zhcd.MainActivity;
import com.zhihuichengdu.songwei.zhcd.R;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/21.
 */
public class MainContentFragment extends BaseFragment {
    private NoScrollView mViewPager;
    private ArrayList<BasePage> basePageArrayList;
    private RadioGroup mRadioGroup;

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.fragment_right, null);
        mViewPager = (NoScrollView) view.findViewById(R.id.fr_viewpager);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.fr_rg);
        return view;
    }

    @Override
    public void initData() {
        basePageArrayList = new ArrayList<BasePage>();
        basePageArrayList.add(new HomePager(activity));
        basePageArrayList.add(new NewsPager(activity));
        basePageArrayList.add(new GovPager(activity));
        basePageArrayList.add(new SerPager(activity));
        basePageArrayList.add(new SettingPager(activity));

        mViewPager.setAdapter(new ViewPagerAdapter());

//对不同的RadioButton设置不同的点击事件
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_gov:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_service:
                        mViewPager.setCurrentItem(3, true);
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, true);
                        break;
                }
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                BasePage basePage = basePageArrayList.get(position);
                basePage.init_data();

                if (position == 0 || position == basePageArrayList.size() - 1) {
                    EnabledSlidingMenu(false);
                } else {
                    EnabledSlidingMenu(true);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        basePageArrayList.get(0).init_data();    //手动加载‘首页’  其余的页面当点击的时候再加载
    }

    public void EnabledSlidingMenu(boolean enabled) {            //设置侧边栏在什么时候能拉出，或者不能拉出
        MainActivity mainUI = (MainActivity) activity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        if (enabled) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    public class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return basePageArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePage basePage = basePageArrayList.get(position);
          // View view1=  basePage.init_view();
            View view = basePage.mRootView;
            basePage.init_data();                       //不同的View初始化的布局数据不同
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public NewsPager getNewsPager() {
        return (NewsPager) basePageArrayList.get(1);
    }
}
