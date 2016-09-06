package com.zhihuichengdu.songwei.zhcd;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zhuhuichengdu.songwei.fragment.LeftMenuFragment;
import com.zhuhuichengdu.songwei.fragment.MainContentFragment;

public class MainActivity extends SlidingFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();

        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸
        slidingMenu.setBehindOffset(200);//屏幕预留200像素宽度

        initFragmant();

    }

    private void initFragmant() {
        FragmentManager manager = getFragmentManager();   //拿到fragmanamger对象
        // FragmentManager manager= getSupportFragmentManager();   //拿到fragmanamger对象
        FragmentTransaction fragmentTransaction = manager.beginTransaction();//声明一个事物
        fragmentTransaction.replace(R.id.fl_left, new LeftMenuFragment(), "left_menu");
        fragmentTransaction.replace(R.id.fl_main, new MainContentFragment(), "main_content");
        fragmentTransaction.commit();//提交事物
    }

    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getFragmentManager();
        return (LeftMenuFragment) fm.findFragmentByTag("left_menu");
    }
  public  MainContentFragment getMainContentFragment(){
      FragmentManager fm=getFragmentManager();
      return (MainContentFragment) fm.findFragmentByTag("main_content");
  }

}
