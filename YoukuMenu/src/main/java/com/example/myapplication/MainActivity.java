package com.example.myapplication;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";


    private boolean isShowLevel2 = true;
    private boolean isShowLevel3 = true;
    private boolean isShowAll = true;

    private Button button;
    private ImageView ivHome;
    private ImageView ivMenu;
    private RelativeLayout rlLevel1;
    private RelativeLayout rlLevel2;
    private RelativeLayout rlLevel3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.bt_test1);
        ivHome = (ImageView)findViewById(R.id.iv_home);
        ivMenu = (ImageView)findViewById(R.id.iv_menu);

        rlLevel1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rlLevel2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rlLevel3 = (RelativeLayout) findViewById(R.id.rl_level3);

    }

    private void initListener() {

        button.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        ivMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int animDelay = 0;
        switch (v.getId()){
            case R.id.bt_test1:

                if (isShowAll){
                    if(isShowLevel3){
                        AnimUtil.closeMenu(rlLevel3,animDelay);
                        animDelay += 200;
                        isShowLevel3 = false;
                    }

                    if(isShowLevel2){
                        AnimUtil.closeMenu(rlLevel2,animDelay);
                        animDelay += 200;
                        isShowLevel2 = false;
                    }
                    AnimUtil.closeMenu(rlLevel1,animDelay);

                }else{
                    AnimUtil.showMenu(rlLevel1,animDelay);
                    animDelay += 200;
                    AnimUtil.showMenu(rlLevel2,animDelay);
                    isShowLevel2 = true;
                    animDelay += 200;
                    AnimUtil.showMenu(rlLevel3,animDelay);
                    isShowLevel3 = true;
                }
                isShowAll = !isShowAll;
                break;
            case R.id.iv_home:
                if (isShowLevel2){
                    Log.d(TAG, "onClick: no show");
                    if (isShowLevel3){
                        AnimUtil.closeMenu(rlLevel3,animDelay);
                        animDelay += 200;
                        isShowLevel3 = false;
                    }
                    AnimUtil.closeMenu(rlLevel2,animDelay);

                }else{
                    Log.d(TAG, "onClick: show");
                    AnimUtil.showMenu(rlLevel2,0);
                }
                isShowLevel2 = !isShowLevel2;
                break;

            case R.id.iv_menu:
                if (isShowLevel3){
                    AnimUtil.closeMenu(rlLevel3,0);
                }else{
                    AnimUtil.showMenu(rlLevel3,0);
                }
                isShowLevel3 = !isShowLevel3;

                break;

        }
    }
}
