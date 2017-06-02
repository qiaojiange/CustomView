package com.example.myapplication;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by qiaojiange on 2017/6/1.
 */

public class AnimUtil {

    public static void closeMenu(RelativeLayout r,int startOffset){
        for (int i = 0; i < r.getChildCount(); i++) {
            View v = r.getChildAt(i);
            v.setEnabled(false);
        }

        RotateAnimation animation = new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,1.0f);
        animation.setDuration(400);
        //动画结束后，保存原来的状态
        animation.setFillAfter(true);
        animation.setStartOffset(startOffset);
        r.startAnimation(animation);
    }

    public static void showMenu(RelativeLayout r,int startOffset){
        for (int i = 0; i < r.getChildCount(); i++) {
            View v = r.getChildAt(i);
            v.setEnabled(true);
        }
        RotateAnimation animation = new RotateAnimation(-180,0,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,1.0f);
        animation.setDuration(400);
        animation.setStartOffset(startOffset);
        animation.setFillAfter(true);
        r.startAnimation(animation);
    }
}
