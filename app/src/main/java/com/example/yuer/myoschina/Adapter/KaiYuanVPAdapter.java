package com.example.yuer.myoschina.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class KaiYuanVPAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public KaiYuanVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
                return "分类";
            case 1:
                return "推荐";
            case 2:
                return "最新";
            case 3:
                return "热门";
            case 4:
                return "国产";

        }
        return super.getPageTitle(position);
    }
}
