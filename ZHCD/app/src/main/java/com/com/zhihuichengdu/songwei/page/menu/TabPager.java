package com.com.zhihuichengdu.songwei.page.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.com.zhihuichengdu.songwei.bean.GetData;
import com.com.zhihuichengdu.songwei.bean.TopNewsBean;
import com.com.zhihuichengdu.songwei.page.BaseMenuDetailPager;
import com.com.zhihuichengdu.songwei.utils.Config;
import com.com.zhihuichengdu.songwei.view.MyListView;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.CirclePageIndicator;
import com.zhihuichengdu.songwei.zhcd.R;

import java.util.ArrayList;

/**
 * Created by songwei on 2016/8/26.
 */
public class TabPager extends BaseMenuDetailPager
{
     private  GetData.Left_Menu mleft_menu;

    @ViewInject(R.id.top_news_viewpager)
     private ViewPager viewPager;

    @ViewInject(R.id.indicator)
     private CirclePageIndicator indicator;

    @ViewInject(R.id.top_news_list)
    private MyListView TopNewsList;

    @ViewInject(R.id.top_news_message)
    private TextView topNewMessage;

    private ArrayList<TopNewsBean> topnewsbean;

    private ArrayList<TopNewsBean.TopNewsnews> topnewsnews ;
    private  ArrayList<TopNewsBean.NewsMessage> news;

    public TabPager(Activity activity, GetData.Left_Menu left_menu)
    {
        super(activity);
        mleft_menu=left_menu;
    }

    @Override
    public View init_view() {
        View view = View.inflate(mactivity, R.layout.top_news,null);
        ViewUtils.inject(this,view);

        View listview = View.inflate(mactivity,R.layout.top_news_listheader,null);  //声明viewpager的头布局
       // viewPager= (ViewPager) listview.findViewById(R.id.top_news_viewpager);
        ViewUtils.inject(this,listview);
        TopNewsList.addHeaderView(listview);                     //将头条新闻以listview的项目头 添加到listview中,实现直接下拉功能

        TopNewsList.setOnRefreshListener(new MyListView.OnRefreshListener() {    //为自定义的listview设置回调事件
            @Override
            public void onRefresh() {
                getDataFromServer();
            }
        });
        return view;
    }

    @Override
    public void init_data() {
        getDataFromServer();
       //System.out.println(" topnewsnews.size()= "+ topnewsnews.size());
    }

    private void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Config.Server + mleft_menu.url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println("-------------load image url is :" + Config.Server + mleft_menu.url);
                System.out.println("--------------load image result is :" + result);
                ConvertGson(result);

                TopNewsList.RefreshCompelete(true);    //当请求数据成功，产生回调，通知UI更新
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mactivity, s.toString(), Toast.LENGTH_SHORT).show();
                TopNewsList.RefreshCompelete(false);                    //当请求数据失败，产生回调
            }
        });
    }

    public  void ConvertGson(String result){
        Gson gson  = new Gson();
        TopNewsBean topnewsbean= gson.fromJson(result, TopNewsBean.class);
        // TODO: 2016/8/26   I have get json data and the request url is correct next is ConvertGson

        topnewsnews=topnewsbean.data.topnews;         //将头条新闻的相关信息封装到集合中、
        news=topnewsbean.data.news;               //将新闻列表的相关信息封装到集合中

            if(topnewsnews!=null)
            {
                viewPager.setAdapter(new TopViewPager());          //对头条新闻的图片设置适配器
                System.out.println("topnewsnews.size() =" + topnewsnews.size());
                indicator.setViewPager(viewPager);               //直接使用框架中的小圆点，必须设置snap属性为真
                indicator.setSnap(true);
                indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        topNewMessage.setText(topnewsnews.get(position).title);     //在页面切换时间中，头条新闻页面切换时，图片上对应的文字也需要切换
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                topNewMessage.setText(topnewsnews.get(0).title);                     //默认加载第一项的标题


            }

        if(news!=null){
            TopNewsList.setAdapter(new TopListAdapter());           //添加listview适配器

        }

    }

    public  class  TopListAdapter extends BaseAdapter{
         private  ViewHolder viewholer;
         private  BitmapUtils mBitmapUtils;
        public  TopListAdapter(){
            mBitmapUtils= new BitmapUtils(mactivity);
            mBitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
        }
        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public TopNewsBean.NewsMessage getItem(int i) {

            return news.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view==null){
               view=View.inflate(mactivity,R.layout.top_news_item,null);
                viewholer = new ViewHolder();
                viewholer.top_news_image= (ImageView) view.findViewById(R.id.top_news_image);
                viewholer.top_new_title= (TextView) view.findViewById(R.id.top_news_title);
                viewholer.top_news_date= (TextView) view.findViewById(R.id.top_news_date);
            }
            //对单个listitem进行适配
            TopNewsBean.NewsMessage newsMessage = getItem(i);
            viewholer.top_new_title.setText(newsMessage.title);
            viewholer.top_news_date.setText(newsMessage.pubdate);

            String cutimageurl=newsMessage.listimage.substring(25);
            System.out.println("newsMessage.listimage is :"+newsMessage.listimage);
            mBitmapUtils.display(viewholer.top_news_image,Config.Server+cutimageurl);
            return view;
        }
    }

    public  static class ViewHolder{
        ImageView top_news_image;
        TextView top_new_title,top_news_date;
    }


    public class TopViewPager extends PagerAdapter {
        private BitmapUtils bitmap;

        public TopViewPager() {
            bitmap = new BitmapUtils(mactivity);
            bitmap.configDefaultLoadFailedImage(R.drawable.topnews_item_default);

        }

        @Override
        public int getCount() {
            return topnewsnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mactivity);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            String imageurl =topnewsnews.get(position).topimage;
            //System.out.println("---------load imageurl is :" +imageurl);
            String cuturl=imageurl.substring(25);
            //System.out.println("---------cut imageurl is :" +Config.Server+cuturl);
            bitmap.display(imageView, Config.Server + cuturl);
            container.addView(imageView);

            return  imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((View) object);
        }
    }
}
