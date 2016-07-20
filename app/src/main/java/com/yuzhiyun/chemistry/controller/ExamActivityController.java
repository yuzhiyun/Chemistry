package com.yuzhiyun.chemistry.controller;

import android.support.v4.app.Fragment;

import com.yuzhiyun.chemistry.view.fragment.ExercisesFragment;

import java.util.ArrayList;

/**
 * Created by yuzhiyun on 2016-07-10.
 */
public class ExamActivityController {
    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();

    public ExamActivityController() {

        fragmentArrayList.add(new ExercisesFragment(0));
        fragmentArrayList.add(new ExercisesFragment(1));
        fragmentArrayList.add(new ExercisesFragment(2));
        fragmentArrayList.add(new ExercisesFragment(3));
    }

    public ArrayList<Fragment> getFragmentArrayList() {
        return fragmentArrayList;
    }


}
