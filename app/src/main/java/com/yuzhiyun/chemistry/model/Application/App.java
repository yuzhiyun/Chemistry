package com.yuzhiyun.chemistry.model.Application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yuzhiyun.chemistry.model.entity.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by yuzhiyun on 2016-07-19.
 */
public class App extends Application {
    private static App app = null;
String TAG;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        TAG=this.getClass().getSimpleName();
    }

    //	获取现在的用户BmobUser.getCurrentUser
    public User getCurrentUser() {

        User user = BmobUser.getCurrentUser(app, User.class);
        if (user != null) {
            return user;
        }
        Log.e(TAG,"获取不到user");
        return null;
    }

    public static App getInstance() {
        return app;
    }
}
