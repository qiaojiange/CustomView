package com.example.adplay;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ViewPager viewPager;
    private TextView textView;
    private LinearLayout llDots;

    private ArrayList<Ad> adArrayList = new ArrayList<>();

    private MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
    private int delayTime = 4000;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                int position = viewPager.getCurrentItem()+1;
                Log.d(TAG, "handleMessage: position = "+position);
                viewPager.setCurrentItem(position);
                handler.sendEmptyMessageDelayed(0,delayTime);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updataIntroAndDot();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void updataIntroAndDot() {
        int currentPosition = viewPager.getCurrentItem()%adArrayList.size();

        textView.setText(adArrayList.get(currentPosition).getText());
        for (int i = 0; i < llDots.getChildCount(); i++) {
            llDots.getChildAt(i).setEnabled(i == currentPosition);
        }
    }


    private void initData() {
        adArrayList.add(new Ad(R.mipmap.a, "巩俐不低俗，我就不能低俗"));
        adArrayList.add(new Ad(R.mipmap.b, "朴树又回来了，再唱经典老歌引百万人同唱啊"));
        adArrayList.add(new Ad(R.mipmap.c, "揭秘北京电影如何升级"));
        adArrayList.add(new Ad(R.mipmap.d, "乐视网TV版大放送"));
        adArrayList.add(new Ad(R.mipmap.e, "热血屌丝的反杀"));
        initDots();

        updataIntroAndDot();

        viewPager.setAdapter(myPagerAdapter);
//        解决初始向左滑动,这样就实现了向左向右滑动
        int currPosition = (Integer.MAX_VALUE>>1)- ((Integer.MAX_VALUE>>1)%adArrayList.size());
        Log.d(TAG, "initData: currPosition="+currPosition);

        viewPager.setCurrentItem(currPosition);

        handler.sendEmptyMessageDelayed(0,delayTime);

    }

    //    添加Dot控件
    private void initDots() {
        for (int i = 0; i < adArrayList.size(); i++) {
            View v = new View(this);
//            是谁的布局，就用谁的LayoutParams---切记
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);
            if (i != 0) {
                params.leftMargin = 5;
            }
            v.setLayoutParams(params);
            v.setBackgroundResource(R.drawable.selector_dot);
            llDots.addView(v);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPage);
        textView = (TextView) findViewById(R.id.tv_ad);
        llDots = (LinearLayout) findViewById(R.id.ll_dots);
    }


    class MyPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
         //   Log.d(TAG, "instantiateItem: position = " + position);
            View v = View.inflate(MainActivity.this, R.layout.adapter_ad, null);
            ImageView imageView = (ImageView) v.findViewById(R.id.image);

            int currPosition = position%adArrayList.size();
            int resId = adArrayList.get(currPosition).getResId();
            imageView.setImageResource(resId);

            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        //这个是判断当前的view,下一个view是不是同一个view
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
         //   Log.d(TAG, "destroyItem: "+position);
            container.removeView((View) object);
        }
    }
}
