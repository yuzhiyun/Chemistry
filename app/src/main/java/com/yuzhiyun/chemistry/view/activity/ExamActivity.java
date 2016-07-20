package com.yuzhiyun.chemistry.view.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;


import com.yuzhiyun.chemistry.model.Adapter.ExamAdapter;
import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.ExamActivityController;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class ExamActivity extends BaseActivity {
    String KEY_CHAPTER="Chapter";
    String KEY_TYPE="type";
    TextView tvTitle;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ExamActivityController examActivityController;



    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_exam);
    }

    @Override
    protected void findView() {
        pager= (ViewPager) findViewById(R.id.pager);
        tvTitle= (TextView) findViewById(R.id.tvTitle);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {
//        toolbar.setLogo(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Bundle data = getIntent().getExtras();
        //获取章的名称
        if(data!=null)
            tvTitle.setText(data.getString(KEY_CHAPTER));
        else
            tvTitle.setText("未获取到章节信息");
//        toolbar.setTitle(data.getString(KEY_CHAPTER));
        pager.setAdapter(getPagerAdapter());
    }

    public PagerAdapter getPagerAdapter() {
        examActivityController=new ExamActivityController();
        pagerAdapter=new ExamAdapter(getSupportFragmentManager(),examActivityController.getFragmentArrayList());
        return pagerAdapter;
    }
}
