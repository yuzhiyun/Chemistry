package com.yuzhiyun.chemistry.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


import com.yuzhiyun.chemistry.Adapter.examAdapter;
import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.ExamActivityController;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class ExamActivity extends BaseActivity {
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ExamActivityController examActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_exam);
    }

    @Override
    protected void findView() {
        pager= (ViewPager) findViewById(R.id.pager);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {
//        toolbar.setLogo(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        pager.setAdapter(getPagerAdapter());
    }

    public PagerAdapter getPagerAdapter() {
        examActivityController=new ExamActivityController();
        pagerAdapter=new examAdapter(getSupportFragmentManager(),examActivityController.getFragmentArrayList());
        return pagerAdapter;
    }
}
