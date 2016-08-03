package com.yuzhiyun.chemistry.model.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 惟我独尊 on 2015/12/13.
 */
public class toast {
    public static  void ShowText(String string, Context context){
        Toast.makeText(context, string,Toast.LENGTH_SHORT).show();

    }
}
