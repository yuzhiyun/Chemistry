package com.yuzhiyun.chemistry.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yuzhiyun.chemistry.view.fragment.aFragment;

import java.util.ArrayList;

/**
 * Created by yuzhiyun on 2016-07-10.
 */
public class examAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    public examAdapter(FragmentManager fm,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList=fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}
