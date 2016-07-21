package com.yuzhiyun.chemistry.controller;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.yuzhiyun.chemistry.model.entity.NoChoiceExercise;
import com.yuzhiyun.chemistry.view.fragment.ExercisesFragment;
import com.yuzhiyun.chemistry.model.dao.db;
import com.yuzhiyun.chemistry.view.fragment.NoChoiceExerciseFragment;

import java.util.ArrayList;

/**
 * Created by yuzhiyun on 2016-07-10.
 */
public class ExamActivityController {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    //章
    int chapter;
    //题型
    int type;

    public ExamActivityController() {
    }

    /**********************************************************************************************
     * 作用：得到
     * 参数：
     * chapter 题目章数
     * type  题型
     * 返回值：无
     *******************************************************************************************/

    public ExamActivityController(int chapter, int type) {
        this.chapter = chapter;
        this.type = type;
    }

    public ArrayList<Fragment> getFragmentArrayList() {
        fragmentArrayList.clear();
        db database = new db();
        switch (type) {

            //选择题,使用ExercisesFragment
            case 0:

                int size = database.getChoiceList(chapter + 1).size();
                for (int i = 0; i < size; i++)
                    fragmentArrayList.add(new ExercisesFragment(i, chapter));
                break;
            /**其他题型共用同一个Fragment*/
            //名词解释题
            case 1:
                //简答题
            case 2:
                //计算题
            case 3:
                int size2 = database.getList(type, chapter+1).size();
                if (0 == size2)
                    Log.e("getFragmentArrayList", "未获取到数据");
                for (int i = 0; i < size2; i++)
                    fragmentArrayList.add(new NoChoiceExerciseFragment(i, type, chapter));
                break;
            default:
                break;

        }
        return fragmentArrayList;
    }


}
