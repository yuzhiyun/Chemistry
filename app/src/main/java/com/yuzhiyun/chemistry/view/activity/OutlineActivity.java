package com.yuzhiyun.chemistry.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.util.CONSTANT;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;

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


        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();

        // 启动推送服务
        BmobPush.startWork(context);

        // 创建推送消息的对象
        BmobPushManager bmobPushManager = new BmobPushManager(this);
        // 推送一条消息给所有安装此应用的设备
        bmobPushManager.pushMessageAll("hello");
    }

}
