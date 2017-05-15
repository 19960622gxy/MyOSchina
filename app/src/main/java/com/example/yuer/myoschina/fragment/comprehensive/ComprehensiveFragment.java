package com.example.yuer.myoschina.fragment.comprehensive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuer.myoschina.Adapter.MainVPAdapter;
import com.example.yuer.myoschina.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuer on 2017/5/2.
 */

public class ComprehensiveFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //通过布局文件生成对应的view
        View view = inflater.inflate(R.layout.fragment_comprehensive,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //有了view，去操作
        //找控件
        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tablayout_compre);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.vp_compre);
        //给viewPager设置adapter 重用MainVPAdapter  可以重建
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new NewsFragment());  //开源资讯
        fragmentList.add(new BlogFragment());  //推荐博客
        fragmentList.add(new PostFragment());  //技术问答
        fragmentList.add(new NewsFragment());  //每日一搏

        MainVPAdapter adapter=new MainVPAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);//让tabLayout和viewPager两个有关联



    }


}
