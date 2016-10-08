package com.yuzhiyun.chemistry.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.base.BaseActivity;
import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.model.dao.db;

public class WelcomeActivity extends BaseActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (App.getInstance().getCurrentUser() == null)
                startActivity(new Intent(WelcomeActivity.this, LoginRegisterActivity.class));
            else
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("welcomeActivity","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("welcomeActivity", "onRestart");
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.i("welcomeActivity", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("welcomeActivity", "onResume");
    }

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
        db.copyPictureToData(WelcomeActivity.this);
        handler.sendEmptyMessageDelayed(100, 2000);


    }

}
