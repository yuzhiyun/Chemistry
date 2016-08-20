package com.yuzhiyun.chemistry.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.model.util.CONSTANT;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;


public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    //用于日志
    public String TAG=this.toString();
    public Toolbar toolbar;
    public Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        //设置layout
        setLayoutView();
        context=this;
        Bmob.initialize(this, CONSTANT.BMOB_APP_ID);

        // 使用推送服务时的初始化操作,添加一条BmobInstallation的记录
        BmobInstallation.getCurrentInstallation(App.getInstance()).save();
        // 启动推送服务
        BmobPush.startWork(context);
        //由于在下习惯在activity中使用toolbar，所以在此处处理了一下，请注意，继承这个BaseActivity的时候，布局文件一定要加上toolbar,不然空指针异常
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //之所以设置为“”，是因为我们通常需要把toolbar的title居中显示，由于没有函数直接居中显示，所以把title设置为空字符串，然后有必要的话再在布局文件的toolbar中添加一个居中显示的textView即可
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //findViewById
        findView();
        //设置监听
        setListener();
        Log.i(TAG,"BaseActivity");
//        进行其他初始化操作
        initOther();
    }
    protected abstract void setLayoutView();
    protected abstract void findView();
    protected abstract void setListener();
    protected abstract void initOther();

}
