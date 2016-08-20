package com.yuzhiyun.chemistry.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.model.util.CONSTANT;
import com.yuzhiyun.chemistry.view.base.BaseActivity;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;

public class TestSendActivity extends BaseActivity {

    //activity之间传递数据的key 值
    private static final String KEY_POSITION = "position";
    int position;
    @Override
    protected void setLayoutView() {
        setContentView(R.layout.activity_test_send);
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initOther() {

        position = getIntent().getExtras().getInt(KEY_POSITION, 0);



        String installationId = CONSTANT.userList.get(position).getInstallationId();
        Log.i("position",position+" 并且 installationId="+installationId);
        /**
         * 推送消息给特定用户
         * */
        // 创建推送消息的对象
        BmobPushManager bmobPush = new BmobPushManager(this);
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
        query.addWhereEqualTo("installationId", installationId);
        bmobPush.setQuery(query);
        bmobPush.pushMessage("你好，我叫"+ App.getInstance().getCurrentUser().getUsername());
        // 推送一条消息给所有安装此应用的设备
//        bmobPush.pushMessageAll("hello");
    }

}
