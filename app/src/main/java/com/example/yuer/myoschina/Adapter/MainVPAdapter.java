package com.example.yuer.myoschina.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class MainVPAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public MainVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "开源资讯";
            case 1:
                return "推荐博客";
            case 2:
                return "技术问答";
            case 3:
                return "每日一博";

        }
        return super.getPageTitle(position);
    }
}
