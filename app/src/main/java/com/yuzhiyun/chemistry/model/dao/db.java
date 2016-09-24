package com.yuzhiyun.chemistry.model.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.yuzhiyun.chemistry.model.entity.Exercise;
import com.yuzhiyun.chemistry.model.entity.NoChoiceExercise;
import com.yuzhiyun.chemistry.model.util.CONSTANT;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by yuzhiyun on 2016-07-10.
 */
public class db {


    public static String DATA_PATH = "/data/data/com.yuzhiyun.chemistry/";
    static String DB_PATH = "/data/data/com.yuzhiyun.chemistry/databases/";
    static String DB_NAME = "sqlite3.db";
    //    static String DB_NAME = "sqlite.db";
    private SQLiteDatabase db;
    //选择题list
    ArrayList<Exercise> arrayList = new ArrayList();
    //其他三种题型的list
    ArrayList<NoChoiceExercise> NoChoiceExerciseList = new ArrayList();

    public db() {
        this.db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**********************************************************************************************
     * 作用：从数据库获取指定章的选择题
     * 参数：
     * chapter 章
     * 返回值：无
     *******************************************************************************************/
    public ArrayList<Exercise> getChoiceList(int chapter) {
        Cursor cursor = db.rawQuery("select * from " + CONSTANT.table[0] + " where " + table.CHAPTER + "= " + chapter, null);
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
                list_data.setAnswer5(cursor.getString(cursor.getColumnIndex(table.ANSWER5)));
                list_data.setRightAnswer(cursor.getInt(cursor.getColumnIndex(table.RIGHT_ANSWER)));
                arrayList.add(list_data);
                Log.i("db cursor", "question: " + cursor.getString(cursor.getColumnIndex(table.QUESTION))
                        + "answer1: " + cursor.getString(cursor.getColumnIndex(table.ANSWER1)));
                Log.i("db", "数据库内容 ---" + list_data.getQuestion() + "题目");
            }
        }
        return arrayList;
    }

    /**********************************************************************************************
     * 作用：把数据库文件拷贝到手机的data目录
     * 参数：无
     * 返回值：无
     *******************************************************************************************/
    public static void copyToSD(Context context) {
        if ((new File(DB_PATH, DB_NAME).exists()) == false) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
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

                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("db", "数据库复制成功");
        }
        Log.i("db", "数据库文件已经存在吗？ " + (new File(DB_PATH, DB_NAME).exists()));
    }

    /**********************************************************************************
     * 复制Assets下chapter1图片到data目录
     *
     * @param context
     *********************************************************************************/
    public static void copyPictureToData(Context context) {
        String PACKAGE_PATH = "/data/data/com.yuzhiyun.chemistry/";
        String[] pictures = null;
        if ((new File(PACKAGE_PATH + "picture/").exists()) == false) {

//            此处特别注意，创建目录要一级一级的创建，而不是一下子创建很深的子目录
            File dir = new File(PACKAGE_PATH + "picture/");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File dir2 = new File(PACKAGE_PATH + "picture/chapter1/");
            if (!dir2.exists()) {
                dir2.mkdir();
            }
            InputStream is = null;
            try {
//                    获取所有图片文件名
                pictures = context.getAssets().list("picture/chapter1");
                for (int i = 0; i < pictures.length; i++)
                    Log.w("picture", pictures[i]);
//                打印结果，1.gif,2.gif等等
                for (int i = 0; i < pictures.length; i++) {

                    is = context.getAssets().open("picture/chapter1/" + pictures[i]);

                    File file = new File(PACKAGE_PATH + "picture/chapter1/" + pictures[i]);
                    OutputStream os = new FileOutputStream(file);
                    byte[] buffer = new byte[2014];
                    int length;

                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                    os.flush();
                    os.close();
                    is.close();
                }
                Log.w("copy", "gif图片复制成功");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.w("copy", "gif图片文件已经存在吗？ " + (new File(PACKAGE_PATH + "picture/").exists()));
    }

    /**********************************************************************************************
     * 作用：从数据库获取指定章和除去选择题题型的题目list
     * 参数：
     * type    题型
     * chapter 章
     * 返回值：无
     *******************************************************************************************/

    public ArrayList<NoChoiceExercise> getList(int type, int chapter) {
        Cursor cursor = db.rawQuery("select * from " + CONSTANT.table[type] + " where " + table.CHAPTER + "= " + chapter, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                NoChoiceExercise list_data = new NoChoiceExercise();
//                cursor.
                list_data.setQuestion(cursor.getString(cursor.getColumnIndex(table.QUESTION)));
                list_data.setAnswer(cursor.getString(cursor.getColumnIndex(table.ANSWER)));
                NoChoiceExerciseList.add(list_data);
                Log.i("db cursor", "question: " + cursor.getString(cursor.getColumnIndex(table.QUESTION))
                        + "answer: " + cursor.getString(cursor.getColumnIndex(table.ANSWER)));
                Log.i("db", "数据库内容 ---" + list_data.getQuestion() + "题目");
            }
        }
        return NoChoiceExerciseList;
    }
}
