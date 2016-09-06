package com.zhuhuichengdu.songwei.fragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.com.zhihuichengdu.songwei.bean.GetData;
import com.com.zhihuichengdu.songwei.page.impl.NewsPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhihuichengdu.songwei.zhcd.MainActivity;
import com.zhihuichengdu.songwei.zhcd.R;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/21.
 */
public class LeftMenuFragment extends BaseFragment {

    private ArrayList<GetData.NewsTabData> mdata;
    @ViewInject(R.id.lf_listview)
    private ListView listView;
    private int CurrentPosition;
    private LeftMenuAdapter leftMenuAdapter;
    private MainActivity MainUI;


    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view = View.inflate(activity, R.layout.fragment_left, null);
        ViewUtils.inject(this, view);
        return view;
    }

    public void setMenuData(ArrayList<GetData.NewsTabData> data) {
        CurrentPosition = 0;               //每次重新加载的时候清0，防止在RadioGroup中点击，在leftmenu点击出现重复
        mdata = data;
        leftMenuAdapter = new LeftMenuAdapter();
        listView.setAdapter(leftMenuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CurrentPosition = i;
                leftMenuAdapter.notifyDataSetChanged();            //通知list刷新
                toogle();                                            //if slidingmenu开启，则关闭
                setCurrentDetailPager(i);
            }


        });

    }

    /*
        setCurrentDetailPager
        通过主线程，Mainactivity中的getMainContentFragment方法，得到ContentFragment,再得到NewsPager
        此方法主要是进入NewsPager中，将left菜单中的详情 在NewsPager中详细展示
     */
    private void setCurrentDetailPager(int i) {
        MainUI = (MainActivity) activity;
        MainContentFragment mainContentFragment = MainUI.getMainContentFragment();
        NewsPager newsPager = mainContentFragment.getNewsPager();
        newsPager.setCurrentPager(i);
    }

    public void toogle() {
        MainUI = (MainActivity) activity;
        SlidingMenu menu = MainUI.getSlidingMenu();
        menu.toggle();
    }

    public class LeftMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mdata.size();
        }

        @Override
        public GetData.NewsTabData getItem(int i) {
            return mdata.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View currentview = View.inflate(activity, R.layout.lf_listview_item, null);
            TextView textView = (TextView) currentview.findViewById(R.id.lf_listview_itemname);
            GetData.NewsTabData item = getItem(i);
            textView.setText(item.title);

            if (CurrentPosition == i) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
            return currentview;
        }
    }
}
