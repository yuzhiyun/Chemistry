package com.yuzhiyun.chemistry.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yuzhiyun.chemistry.model.Application.App;
import com.yuzhiyun.chemistry.model.entity.Exercise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by yuzhiyun on 2016-07-10.
 */
public class db {


    static String DB_PATH = "/data/data/com.yuzhiyun.chemistry/databases/";
    static String DB_NAME = "sqlite3.db";
//    static String DB_NAME = "sqlite.db";
    //选择题表名
    String TABLE_CHOICE="choiceQuestion";
    private SQLiteDatabase db;
    ArrayList<Exercise> arrayList=new ArrayList() ;
    public db() {
        this.db = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);

//        arrayList.add(new Exercise(1,"Activity的生命周期是哪些？","onCreate","onRestart","onResume","onDestroy",1));
//        arrayList.add(new Exercise(2,"别vndu大部分的白癜风","5346","3654","46456","645 东莞东64576",2));
//        arrayList.add(new Exercise(3,"大概后天发回复","呃呃呃6","3654","46456","人人",3));
//        arrayList.add(new Exercise(4,"第三个非官方","呃呃呃6","人","46456","二哥",4));
    }


    public ArrayList<Exercise> getContentList(){
        Cursor cursor = db.rawQuery("select * from "+TABLE_CHOICE,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Exercise list_data = new Exercise();
//                cursor.
                list_data.setQuestion(cursor.getString(cursor.getColumnIndex(table.QUESTION)));
                list_data.setAnswer1(cursor.getString(cursor.getColumnIndex(table.ANSWER1)));
                list_data.setAnswer2(cursor.getString(cursor.getColumnIndex(table.ANSWER2)));
                list_data.setAnswer3(cursor.getString(cursor.getColumnIndex(table.ANSWER3)));
                list_data.setAnswer4(cursor.getString(cursor.getColumnIndex(table.ANSWER4)));
                list_data.setRightAnswer(cursor.getInt(cursor.getColumnIndex(table.RIGHT_ANSWER)));
                arrayList.add(list_data);
                Log.i("db cursor", "question: " + cursor.getString(cursor.getColumnIndex(table.QUESTION))
                        + "answer1: " + cursor.getString(cursor.getColumnIndex(table.ANSWER1)));
                Log.i("db", "数据库内容 ---"+list_data.getQuestion() + "题目");
            }
        }
        return arrayList;
    }
    /**********************************************************************************************
     * 作用：把数据库文件拷贝到手机的data目录
     * 参数：无
     * 返回值：无
     * *******************************************************************************************/
     public  static void copyToSD(Context context){
        if ((new File(DB_PATH,DB_NAME).exists()) == false)
        {
            File dir = new File(DB_PATH);
            if (!dir.exists())
            {
                dir.mkdir();
            }
            try {
                InputStream is = null;
                try {
                    is = context.getAssets().open(DB_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
                byte[] buffer = new byte[2014];
                int length;

                while ((length = is.read(buffer)) > 0)
                {
                    os.write(buffer,0,length);
                }
                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("db","数据库复制成功");
        }
         Log.i("db","数据库文件已经存在吗？ "+(new File(DB_PATH,DB_NAME).exists()));
    }
}
