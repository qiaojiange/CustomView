package com.example.customview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;


/**
 * Created by qiaojiange on 2017/5/30.
 */

public class AnimUtil {

//    问题1：为了解决快速连续双击按钮时动画播放不完，出现的抖动的效果，使用一个计数器animCount标记动画开始个数，
//    开始一个计数器加1，播放完一个计数器减1。在Activity中调用时，判断animCount的个数


    public static int animCount = 0;


    public static void closeMenu(RelativeLayout rl,long startOffset) {
        //让父控件内部的所有子控件禁用
        for (int i = 0; i < rl.getChildCount(); i++) {
            View v = rl.getChildAt(i);
            v.setEnabled(false);
        }
        //逆时针旋转180度
        RotateAnimation animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(500);
//        动画结束后保存原来的状态
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);
        animation.setAnimationListener(new MyAnimationListener());
        rl.startAnimation(animation);
    }


    public static void showMenu(RelativeLayout relativeLayout, long startOffset) {
        //让父控件内的所有子控件启用
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            View v = relativeLayout.getChildAt(i);
            v.setEnabled(true);
        }

        //顺时针旋转180度
        RotateAnimation animation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(500);
        //动画结束后保存原来的状态
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);
        animation.setAnimationListener(new MyAnimationListener());
        relativeLayout.startAnimation(animation);
    }


//    自定义动画监听器
    static class MyAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            animCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            animCount--;
        }
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
