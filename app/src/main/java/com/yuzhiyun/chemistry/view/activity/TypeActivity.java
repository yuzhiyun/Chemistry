package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

import java.util.ArrayList;

public class TypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    String[] data = {"选择题", "名词解释题", "简答题", "计算题"};
    ArrayAdapter<String> adapter;
    String KEY_TYPE = "type";
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
        adapter = new ArrayAdapter<String>(TypeActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        intent = new Intent(TypeActivity.this, ExamActivity.class);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(position);
    }

    void startActivity(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TYPE, data[position]);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
