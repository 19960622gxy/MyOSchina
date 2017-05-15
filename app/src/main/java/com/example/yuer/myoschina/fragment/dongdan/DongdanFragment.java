package com.example.yuer.myoschina.fragment.dongdan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuer.myoschina.Adapter.MainVPAdapter;
import com.example.yuer.myoschina.Adapter.MaindongdanVPAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.fragment.comprehensive.BlogFragment;
import com.example.yuer.myoschina.fragment.comprehensive.NewsFragment;
import com.example.yuer.myoschina.fragment.comprehensive.PostFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuer on 2017/5/3.
 */

public class DongdanFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //通过布局文件生成对应的view
        View view = inflater.inflate(R.layout.fragment_dongdan,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //有了view，去操作
        //找控件
        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tablayout_dongdan);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.vp_dongdan);
        //给viewPager设置adapter 重用MainVPAdapter  可以重建
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new ZuixinFragment());  //最新动弹
        fragmentList.add(new RemenFragment());  //热门动弹


        fragmentList.add(new PostFragment());  //每日乱弹
        fragmentList.add(new NewsFragment());  //我的动弹

        MaindongdanVPAdapter adapter=new MaindongdanVPAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);//让tabLayout和viewPager两个有关联



    }

}
