package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yuzhiyun.chemistry.R;

public class TestActivity extends AppCompatActivity {
    /**
     * 打开一个       Activity：onCreate 》 onStart 》onResume
     * 打开后返回前面的Activity: onPause  》 onStop  》onDestroy
     * 打开后按home键         ：onPause  》 onStop 再次打开 onRestart 》 onStart 》onResume
     * 打开后切换到其他app和打开后  按home键情况一样。
     * 打开后进入其他新的Activity也和按home键情况一样。
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.i("TestActivity", "onCreate");
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this,LoginActivity.class));
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TestActivity", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TestActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TestActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TestActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TestActivity", "onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TestActivity", "onDestroy*********************************");
    }



}
