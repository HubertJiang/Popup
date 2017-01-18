package com.android.popup;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.popup_window, null));
        popupWindow.setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(this, R.color.half)));//需要设置，不设置可能会造成返回键不起作用
//        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));
        popupWindow.setFocusable(false);//物理键是否响应，为true时，点返回键popupWindow消失，为false时，点返回键activity消失。
        popupWindow.setOutsideTouchable(true);//点击popupWindow外面消失
        findViewById(R.id.second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT == 24) {//在android 7.0中,当popupWindow的高度过大时，
                    // 调用showAsDropDown方法popupWindow可能会出现在view的上方或占满全屏，这是android 7.0的bug，用这种方式可以正常显示，
                    // 7.1已经修复这个bug
                    int[] a = new int[2];
                    v.getLocationInWindow(a);
                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + v.getHeight());
                } else {
                    popupWindow.showAsDropDown(v);
                }


            }
        });


        final ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setWidth(getResources().getDisplayMetrics().widthPixels);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        listPopupWindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.half)));
        listPopupWindow.setAdapter(new PopupWindowAdapter(this));
        listPopupWindow.setAnchorView(findViewById(R.id.popup));
        listPopupWindow.setModal(true);
        listPopupWindow.setHorizontalOffset(100);//垂直间距
        listPopupWindow.setVerticalOffset(100);//水平间距
        findViewById(R.id.popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });


    }

}
