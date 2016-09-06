package com.zhihuichengdu.songwei.zhcd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    private RelativeLayout mRelative;
    public static boolean isFirstIn=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        mRelative =(RelativeLayout) findViewById(R.id.splash_relative);
        ScaleAnimation mScaleAnimation = new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
             mScaleAnimation.setDuration(1000);
             mScaleAnimation.setFillAfter(true);
        AlphaAnimation mAlphaAnimation = new AlphaAnimation(0,1);
             mAlphaAnimation.setFillAfter(true);
             mAlphaAnimation.setDuration(2000);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setFillAfter(true);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(mAlphaAnimation);
        animationSet.addAnimation(mScaleAnimation);
        animationSet.addAnimation(rotateAnimation);

        mRelative.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent;
               if(isFirstIn == true){
                   intent = new Intent(SplashActivity.this,MainActivity.class);
                   startActivity(intent);
               }
                else if(isFirstIn==false){
                   intent = new Intent(SplashActivity.this,GuideActivity.class);
                   startActivity(intent);
                   isFirstIn=true;
               }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
