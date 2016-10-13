package com.yuzhiyun.chemistry.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.base.BaseActivity;
import com.yuzhiyun.chemistry.model.util.CONSTANT;

public class TypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    ListView listView;

    ArrayAdapter<String> adapter;
    String KEY_TYPE = "type";
    String KEY_CHAPTER="Chapter";
    Intent intent;

//    int chapter=0;
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_type);
    }

    @Override
    protected void findView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void setListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {

//        chapter=getIntent().getExtras().getInt(KEY_CHAPTER,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("选择题型");
        adapter = new ArrayAdapter<String>(TypeActivity.this, android.R.layout.simple_list_item_1, CONSTANT.type);
        listView.setAdapter(adapter);
        intent = new Intent(TypeActivity.this, ExamActivity.class);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(position);
    }

    void startActivity(int position) {
        Bundle bundle = new Bundle();
        //题型
        bundle.putInt(KEY_TYPE, position);
        //从ChapterActivity传递过来的章数继续传递给下一个Activity
        bundle.putInt(KEY_CHAPTER,CONSTANT.chapter);
        /**
         * 章节这里如果使用intent来传值会出现一个问题
         * 选择章节后再选择题型进去做题没问题，
         * 但是如果再按左上角的返回键再选一次题型就会崩溃
         * 因为如果通过intent获取章节数据，该activity的父activity就变成了做题activity，所有获取不到章节
         * 但是如果做题后是通过back键返回的，该activity的父activity就依然是ChacpterActivity
         * 所以不报错
         * 最终我决定使用CONSTANT类的静态变量来记录chapter
         * */
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
