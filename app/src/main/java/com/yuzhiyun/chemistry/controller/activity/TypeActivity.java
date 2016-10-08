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
        bundle.putInt(KEY_CHAPTER,getIntent().getExtras().getInt(KEY_CHAPTER,0));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
