package com.zhihuichengdu.songwei.zhcd;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private Button mButton;
    private ArrayList<ImageView> imageViewArrayList;
  private   int[] imageid=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mButton = (Button)findViewById(R.id.guide_start);

          init_data();
         mViewPager.setAdapter(new MyPagerAdapter());
         mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {
                 if (position == imageViewArrayList.size() - 1) {
                     mButton.setVisibility(View.VISIBLE);
                 } else {
                     mButton.setVisibility(View.INVISIBLE);
                 }
             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init_data() {
        imageViewArrayList = new ArrayList<ImageView>();
        for(int i=0;i<imageid.length;i++){
            ImageView view = new ImageView(this);
            view.setBackgroundResource(imageid[i]);
            imageViewArrayList.add(view);
        }
    }

     class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView(imageViewArrayList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           container.addView(imageViewArrayList.get(position));
            return imageViewArrayList.get(position);

        }
    }
}
