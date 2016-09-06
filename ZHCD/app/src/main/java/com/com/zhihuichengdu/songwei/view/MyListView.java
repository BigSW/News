package com.com.zhihuichengdu.songwei.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhihuichengdu.songwei.zhcd.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by songwei on 2016/8/30.
 */
public class MyListView extends ListView {
    private View view;             //得到该页面的布局对象

    int viewheight;              //得到该页面布局的整个高度
    private int startY=0;
    private int endY;

    private static final int PULL_REFRESH =1;
    private static final int RELASE_REFRESH =2;
    private static final int REFRESHING =3;
    private  int CurrentStatus= PULL_REFRESH;

    private TextView ListView_title,ListView_date;
    private ImageView ListView_icon;
    private ProgressBar ListView_progressbar;

    private  RotateAnimation animation_up,animation_down;

    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        view = View.inflate(getContext(), R.layout.top_news_refresh, null);

        ListView_title = (TextView) view.findViewById(R.id.top_news_refresh_title);
        ListView_date = (TextView) view.findViewById(R.id.top_news_refresh_date);
        ListView_progressbar= (ProgressBar) view.findViewById(R.id.top_news_refresh_progressbar);
        ListView_icon= (ImageView) view.findViewById(R.id.top_news_refresh_red);

        this.addHeaderView(view);
        getdate();
        initAnimation();            //调用动画方法，实现注册
        view.measure(0, 0);               //测量该布局的高度，并隐藏该布局
        viewheight = view.getMeasuredHeight();
        view.setPadding(0, -viewheight, 0, 0);

    }
    public void RefreshCompelete(boolean result){
        if(result==true){
            CurrentStatus=PULL_REFRESH;
            view.setPadding(0,-viewheight,0,0);
            ListView_title.setText("下拉刷新");
            ListView_icon.setVisibility(View.VISIBLE);
            ListView_progressbar.setVisibility(View.INVISIBLE);
            System.out.println("下拉刷新，返回结果成功！");
        }
        else{
            CurrentStatus=PULL_REFRESH;
            view.setPadding(0,0,0,0);
            ListView_progressbar.setVisibility(View.INVISIBLE);
            ListView_title.setText("网络连接异常，请稍后重新刷新!");
            ListView_title.setTextColor(Color.RED);
            ListView_date.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:                   //手指松开事件中，如果状态时松开刷新
                if(CurrentStatus==RELASE_REFRESH){

                    CurrentStatus=REFRESHING;
                    view.setPadding(0, 0, 0, 0);
                    LoadView();

                    if(mlistener!=null){     //事件回调
                        mlistener.onRefresh();
                    }
                }
                break;

            case MotionEvent.ACTION_DOWN:
                startY=(int)ev.getY();
                System.out.println("-----------the startY is :" +startY);
                break;

            case MotionEvent.ACTION_MOVE:
                if (startY==-1){
                     startY=(int)ev.getY();
                 }
                endY=(int)ev.getY();
               // System.out.println("-----------the endY is :" +startY);
                int DisY=endY-startY;

                System.out.println("-----------the endY-startY is :" +DisY);
                System.out.println("-----------the viewheight is :" +viewheight);
                System.out.println("-----------the DisY-viewheight is :" +(DisY-viewheight));
                if(DisY>0&&getFirstVisiblePosition()==0)
                {    //计算下拉的距离，并当为第一个位置下拉时候，显示该布局
                    view.setPadding(0,DisY-viewheight,0,0);

                  if(DisY>0&&CurrentStatus!=RELASE_REFRESH){         //松开刷新
                      CurrentStatus=RELASE_REFRESH;
                      LoadView();

                  }
                    else if(DisY<0&&CurrentStatus!=PULL_REFRESH){ //下拉刷新
                      CurrentStatus=PULL_REFRESH;
                     LoadView();
                  }
                    return  true;
                }

                break;

        }
        return super.onTouchEvent(ev);
    }
    public void LoadView(){
        switch (CurrentStatus){

            case PULL_REFRESH:             //下拉刷新
                ListView_title.setText("下拉刷新");
                ListView_icon.startAnimation(animation_down);
            break;

            case RELASE_REFRESH:               //松开刷新
                ListView_title.setText("松开刷新");
                ListView_icon.startAnimation(animation_up);
            break;

            case REFRESHING :                 //正在刷新
                ListView_title.setText("正在刷新");
                ListView_progressbar.setVisibility(View.VISIBLE);

                ListView_icon.clearAnimation();
                ListView_icon.setVisibility(View.INVISIBLE);     //在设置图片隐藏之前，必须清除动画
            break;
        }
    }

    public  void initAnimation(){
         animation_up = new RotateAnimation(0,-180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
          animation_up.setFillAfter(true);
          animation_up.setDuration(200);

         animation_down = new RotateAnimation(-180,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
          animation_down.setFillAfter(true);
          animation_down.setDuration(200);
    }
    public  void getdate(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
       String currentTime= date.format(new Date());
        ListView_date.setText("最近一次刷新时间："+currentTime);
    }


  //1:声明接口
    public  interface OnRefreshListener{
        public  void onRefresh();
    }
  //2：,声明接口变量，
    private OnRefreshListener mlistener;

    //3：创建方法调用接口
    public void setOnRefreshListener(OnRefreshListener listener){
        mlistener=listener;
    }
}
