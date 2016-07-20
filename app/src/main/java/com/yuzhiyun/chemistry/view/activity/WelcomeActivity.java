package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.dao.db;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }
    };

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {
        toolbar.setVisibility(View.INVISIBLE);
        db.copyToSD(WelcomeActivity.this);
        handler.sendEmptyMessageDelayed(100, 2000);
    }

}
