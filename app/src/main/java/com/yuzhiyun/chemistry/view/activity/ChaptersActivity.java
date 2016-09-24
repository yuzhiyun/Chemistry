package com.yuzhiyun.chemistry.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.util.CONSTANT;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

public class ChaptersActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    ListView listView;
//    TextView tvTitle;
    private ArrayAdapter<String> chapterAdapter;
    Intent intent;
    //数据源

    String KEY_CHAPTER="Chapter";
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_chapters);
    }

    @Override
    protected void findView() {
        listView= (ListView) findViewById(R.id.listView);
//        tvTitle= (TextView) findViewById(R.id.tvTitle);
    }

    @Override
    protected void setListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initOther() {
        chapterAdapter = new ArrayAdapter<String>(ChaptersActivity.this, android.R.layout.simple_list_item_1, CONSTANT.array_data);
        listView.setAdapter(chapterAdapter);
        intent=new Intent(ChaptersActivity.this,TypeActivity.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("选择章节");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(position);
    }
    void startActivity(int position){
        Bundle bundle=new Bundle();
        bundle.putInt(KEY_CHAPTER, position);
        intent.putExtras(bundle);
        startActivity(intent);
        ///
    }
}
