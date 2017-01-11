package com.android.popup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
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

        final ListPopupWindow listPopupWindow=new ListPopupWindow(this);
        listPopupWindow.setBackgroundDrawable(getDrawable(android.R.color.holo_red_dark));
        listPopupWindow.setAdapter(new PopupAdapter(this));
        listPopupWindow.setAnchorView(findViewById(R.id.popup));
        listPopupWindow.setModal(true);
        listPopupWindow.setHeight(100);
        findViewById(R.id.popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });


        final PopupWindow popupWindow=new PopupWindow(this);
        popupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.item_popup,null));
        findViewById(R.id.second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v);
            }
        });
    }

}
