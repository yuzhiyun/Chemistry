package com.yuzhiyun.chemistry.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.controller.base.BaseActivity;
import com.yuzhiyun.chemistry.model.entity.bmobEntity.User;
import com.yuzhiyun.chemistry.model.util.CONSTANT;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class UserListActivity extends BaseActivity {

    TextView tvTitle;
    private static final String KEY_POSITION = "position";
    String[] userName;
    ListView listView;
    private ArrayAdapter<String> UserAdapter;
    Intent intent;
    //
    String KEY_WHICH_FAB = "whichFab";

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_user_list);
    }

    @Override
    protected void findView() {
        listView = (ListView) findViewById(R.id.listView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {
        if (getIntent().getExtras().getString(KEY_WHICH_FAB).equals("fabTestSend")) {
            intent = new Intent(UserListActivity.this, TestSendActivity.class);
            tvTitle.setText("问候其他用户");
        } else {
            intent = new Intent(UserListActivity.this, DataActivity.class);
            tvTitle.setText("查看所有用户做题时间");
        }
        getUserNameList();
    }

    /**
     * 根据listview被点击的item获取到对应的userName，通过startActivity传递给下一个Activity
     *
     * @param position onItemClick传递过来的参数
     */
    void startActivity(int position) {
        Bundle bundle = new Bundle();
//        bundle.putString(KEY_USERNAME,userName[position]);
        bundle.putInt(KEY_POSITION, position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 获取数据，并且处理listView
     */
    public void getUserNameList() {
        //进度条
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("加载用户列表...");
        progressDialog.show();
        BmobQuery<User> query = new BmobQuery<>();
//        query.addWhereEqualTo()

//query.addWhereRelatedTo
        query.findObjects(context, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                //记录全局userList
                CONSTANT.userList = list;
                progressDialog.dismiss();
                String[] names = new String[list.size()];
                for (int i = 0; i < list.size(); i++)
                    names[i] = list.get(i).getUsername().toString();

                userName = names;
                UserAdapter = new ArrayAdapter<String>(UserListActivity.this, android.R.layout.simple_list_item_1, names);
                listView.setAdapter(UserAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(position);
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                progressDialog.setMessage("网络错误");
            }
        });


    }
}
