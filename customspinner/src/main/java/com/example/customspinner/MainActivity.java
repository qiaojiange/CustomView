package com.example.customspinner;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private ImageView ivDown;
    private List<String> infoList;
    private EditText etUserInfo;
    private ListView listView;
    private PopupWindow popupWindow;

    private boolean isInfoEmpty = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initData();

        initListView();

        initListener();
    }

    private void initListView() {
        listView = new ListView(this);
//        listView.setBackgroundResource(R.mipmap.listview_background);
        listView.setDivider(null);
        listView.setVerticalScrollBarEnabled(false);

        listView.setAdapter(new MyListAdapter());
    }

    private void initData() {
        infoList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            infoList.add(900000+i+"");
        }

    }


    private void initListener() {
        ivDown.setOnClickListener(this);

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ivDown = (ImageView) findViewById(R.id.iv_down);
        etUserInfo = (EditText)findViewById(R.id.et_choose_info);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_down:
                Log.d(TAG, "onClick: iv_down");
                if (popupWindow == null){
                    popupWindow = new PopupWindow(MainActivity.this);
                }
                popupWindow.setWidth(etUserInfo.getWidth());
                popupWindow.setHeight(300);

//                这三个图形一个都不能少
                //要让其中的view获取焦点，必须设置为true;
                popupWindow.setFocusable(true);
                //必须设置一个背景图片
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //点击到popupWindow外部区域，自动关闭popWin
                popupWindow.setOutsideTouchable(true);

                //为popWindow填充内容
                popupWindow.setContentView(listView);

                popupWindow.showAsDropDown(etUserInfo,0,2);

                break;

            default:
                break;

        }
    }


    class MyListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return infoList.size();
        }

        @Override
        public Object getItem(int position) {
            return infoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
//            这一部分是模板代码---要记住
            ViewHold holder;
            if (convertView == null){
                View v = View.inflate(MainActivity.this,R.layout.user_item,null);
                final TextView tv = (TextView) v.findViewById(R.id.et_user_info);
                ImageView ivDelete = (ImageView)v.findViewById(R.id.iv_delete);
               // tv.setEnabled(false);

                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: position = "+ position);
                        infoList.remove(position);
                        notifyDataSetChanged();

                        int listViewHigh = tv.getHeight()*infoList.size();


                        popupWindow.setHeight( listViewHigh>popupWindow.getHeight()?popupWindow.getHeight():listViewHigh);
                        if (infoList.size() == 0){
                            popupWindow.dismiss();
                            ivDown.setVisibility(View.GONE);
                        }
                    }
                });

                convertView = v;
                holder = new ViewHold();
                holder.tvUserInfo = tv;
                holder.ivDelete = ivDelete;
                convertView.setTag(holder);

            }else{
                holder = (ViewHold) convertView.getTag();
            }
            holder.tvUserInfo.setText(infoList.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etUserInfo.setText(infoList.get(position));
                    popupWindow.dismiss();
                }
            });
            return convertView;
        }

        class ViewHold{
            TextView tvUserInfo;
            ImageView ivDelete;
        }

    }

}
