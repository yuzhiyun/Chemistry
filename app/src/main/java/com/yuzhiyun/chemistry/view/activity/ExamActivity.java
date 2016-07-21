package com.yuzhiyun.chemistry.view.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


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
    Bundle data;


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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case android.R.id.home:
                        finish();
                        Log.i("setNavigation", "finish");
                        break;
                    default:
                        break;
                }
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        Toast.makeText(context,"finish",Toast.LENGTH_LONG).show();
                        Log.i("setOnMenuItem","finish");
                        finish();
                        break;
//                    case R.id.action_settings:
//                        Toast.makeText(Setting.this, "action_settings", 0).show();
//                        break;
//                    case R.id.action_share:
//                        Toast.makeText(Setting.this, "action_share", 0).show();
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initOther() {

//        toolbar.setLogo(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
         data = getIntent().getExtras();
        //获取章的名称
        int num=data.getInt(KEY_CHAPTER)+1;
        if(data!=null)
            tvTitle.setText("第"+num+"章");
        else
            tvTitle.setText("未获取到章节信息");
//        toolbar.setTitle(data.getString(KEY_CHAPTER));
        pager.setAdapter(getPagerAdapter());
    }

    public PagerAdapter getPagerAdapter() {
//        传递题型、章进去
        int chapter=data.getInt(KEY_CHAPTER);
        int type=data.getInt(KEY_TYPE);
        Log.i(" 传递题型、章",chapter+"  "+type);
        examActivityController=new ExamActivityController(chapter,type);
        pagerAdapter=new ExamAdapter(getSupportFragmentManager(),examActivityController.getFragmentArrayList());
        return pagerAdapter;
    }

}
