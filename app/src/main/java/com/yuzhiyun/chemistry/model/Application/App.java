package com.yuzhiyun.chemistry.model.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuzhiyun on 2016-07-19.
 */
public class App extends Application {
    private static Context context;
    public App() {
    }
     public static Context getInstance(){
         context=new App();
         return context;
     }
}
