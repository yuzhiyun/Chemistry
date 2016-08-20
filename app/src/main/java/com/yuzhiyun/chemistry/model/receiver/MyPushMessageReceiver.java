package com.yuzhiyun.chemistry.model.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yuzhiyun.chemistry.R;
import com.yuzhiyun.chemistry.model.Application.App;

import cn.bmob.push.PushConstants;

/**
 * Created by yuzhiyun on 2016-08-20.
 */
public class MyPushMessageReceiver  extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            String message=intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING).toString();
//            Log.i("bmob", "BmobPushDemo收到消息：" + message);
            Toast.makeText(context, "BmobPushDemo收到消息：" + message, Toast.LENGTH_SHORT).show();
            notification(message);
        }
    }

    private void notification(String message) {

        //首先我们要获得一个通知管理器 NotificationManager,是一个系统的service
        NotificationManager manager =(NotificationManager)App.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        //通过Builer来创建
        Notification notify = new Notification.Builder(App.getInstance())
                .setSmallIcon(R.drawable.ic_doc)//设置小图,大图用setLargeIcon设置
//                .setTiker("Ticker Text")
//                .setContenTitle("拉开后标题")
                .setContentText(message)
               // .setContentIntent(pandingIntent)//点击后触发的事件
                .setNumber(1)//如果同一个通知发送多条可以用这个区分
                .getNotification();//获取通知对象
        //系统通知声音
        notify.defaults=Notification.DEFAULT_SOUND;
        notify.flags|=Notification.FLAG_AUTO_CANCEL;//通知点击后自动消失
         int FlAG_ID=10;
        manager.notify(FlAG_ID,notify);//通过menager发送通知,FLAG_ID是通知的id,自己定义
    }

}