package com.example.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private ImageView ivHome;
    private ImageView ivMenu;
    private Button button;


    private boolean isShowLevel2 = true;//是否显示2级菜单
    private boolean isShowLevel3 = true;//是否显示3级菜单
    private boolean isShowMenu = true;//是否显示整个菜单，包括1级，2级，3级的菜单

    private RelativeLayout rlLevel1;
    private RelativeLayout rlLevel2;
    private RelativeLayout rlLevel3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    //绑定监听器
    private void initListener() {
        ivHome.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        button.setOnClickListener(this);


    }

    //初始化视图
    private void initView() {
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button1);

        ivHome = (ImageView) findViewById(R.id.iv_home);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);

        rlLevel1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rlLevel2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rlLevel3 = (RelativeLayout) findViewById(R.id.rl_level3);
    }



    //绑定事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_home:
                if (AnimUtil.animCount != 0) {
                    return;
                }
                if (isShowLevel2) {
                    Log.d(TAG, "onClick: iv_home 隐藏");
                    //先隐藏三级菜单，之后再隐藏二级菜单
                    int animaDelay = 0;
                    if (isShowLevel3) {
                        AnimUtil.closeMenu(rlLevel3, animaDelay);
                        animaDelay += 200;
                        isShowLevel3 = false;
                    }

                    AnimUtil.closeMenu(rlLevel2, animaDelay);
                } else {
                    Log.d(TAG, "onClick: iv_home 显示");
                    AnimUtil.showMenu(rlLevel2,0);
                }
                isShowLevel2 = !isShowLevel2;
                break;

            case R.id.iv_menu:
                if (AnimUtil.animCount != 0) {
                    return;
                }

                if (isShowLevel3) {
                    AnimUtil.closeMenu(rlLevel3, 0);
                } else {
                    AnimUtil.showMenu(rlLevel3,0);
                }
                isShowLevel3 = !isShowLevel3;
                break;

            case R.id.button1:
                if(isShowMenu){
                    int animDelay = 0;
                    if(isShowLevel3){
                        AnimUtil.closeMenu(rlLevel3,animDelay);
                        animDelay += 200;
                        isShowLevel3 = false;
                    }

                    if (isShowLevel2){
                        AnimUtil.closeMenu(rlLevel2,animDelay);
                        animDelay+=200;
                        isShowLevel2 = false;
                    }

                    AnimUtil.closeMenu(rlLevel1,animDelay);
                }else{
                    int animDelay = 0;
                    AnimUtil.showMenu(rlLevel1,animDelay);
                    animDelay += 200;

                    AnimUtil.showMenu(rlLevel2,animDelay);
                    animDelay += 200;
                    isShowLevel2 = true;

                    AnimUtil.showMenu(rlLevel3,animDelay);
                    isShowLevel3 = true;
                }
                isShowMenu = !isShowMenu;
                break;
        }
    }
}
