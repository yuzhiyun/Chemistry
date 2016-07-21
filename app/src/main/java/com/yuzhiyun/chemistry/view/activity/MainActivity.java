package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.dao.db;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    Button btnExam;
    Button btnAbout;
TextView tvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findView() {
        btnExam= (Button) findViewById(R.id.btnExam);
        btnAbout= (Button) findViewById(R.id.btnAbout);
        tvTest= (TextView) findViewById(R.id.tvTest);

    }

    @Override
    protected void setListener() {
        btnExam.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    protected void initOther() {
        toolbar.setTitle("习题宝典");
        //空格\t,换行\n
        tvTest.setText("第一行\n\t\t\t第二行\n\t\t\t\t\t\t第三行");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExam:
                startActivity(new Intent(context,ChaptersActivity.class));
                break;
            case R.id.btnAbout:
                startActivity(new Intent(context,OutlineActivity.class));
                break;
        }
    }
}
