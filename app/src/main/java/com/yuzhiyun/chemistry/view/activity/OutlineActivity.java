package com.yuzhiyun.chemistry.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class OutlineActivity extends BaseActivity {

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_outline);
    }

    @Override
    protected void findView() {
//        TextView textView=

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {
        toolbar.setTitle("教学大纲");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
