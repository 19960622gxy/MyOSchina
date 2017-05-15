package com.example.yuer.myoschina.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.yuer.myoschina.Adapter.MainVPAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.TabBean;
import com.example.yuer.myoschina.fragment.comprehensive.ComprehensiveFragment;
import com.example.yuer.myoschina.fragment.comprehensive.NewsFragment;
import com.example.yuer.myoschina.fragment.dongdan.DongdanFragment;
import com.example.yuer.myoschina.fragment.faxian.FaXianFragment;
import com.example.yuer.myoschina.fragment.wode.WoDeFragment;
import com.example.yuer.myoschina.widget.BottomLayout;
import com.example.yuer.myoschina.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    UnScrollViewPager viewPager;
    MainVPAdapter mainVPAdapter ;
    List<Fragment> fragmentList = new ArrayList<>();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("综合");
        //添加 四大模块 fragment
        fragmentList.add(new ComprehensiveFragment());//综合
        fragmentList.add(new DongdanFragment());      //动弹
        fragmentList.add(new FaXianFragment());         //发现
        fragmentList.add(new WoDeFragment());         //我的

        mainVPAdapter = new MainVPAdapter(getSupportFragmentManager(),fragmentList);
        viewPager = (UnScrollViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(mainVPAdapter);
        viewPager.setOffscreenPageLimit(4);  //页数限制 缓存页数是4页

        BottomLayout bottomLayout = (BottomLayout) findViewById(R.id.bottomLayout);
        List<TabBean> tabs = new ArrayList<>();
        tabs.add(new TabBean("综合",R.mipmap.ic_nav_news_actived,R.mipmap.ic_nav_news_normal,1));
        tabs.add(new TabBean("动弹",R.mipmap.ic_nav_tweet_actived,R.mipmap.ic_nav_tweet_normal,1));
        tabs.add(new TabBean("",R.mipmap.ic_nav_add_actived,R.mipmap.ic_nav_add_normal,0));
        tabs.add(new TabBean("发现",R.mipmap.ic_nav_discover_actived,R.mipmap.ic_nav_discover_normal,1));
        tabs.add(new TabBean("我的",R.mipmap.ic_nav_my_pressed,R.mipmap.ic_nav_my_normal,1));
        //先设点击监听
        bottomLayout.setMidClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "点击了中间", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,PubDongdanActivity.class);
                startActivity(intent);

            }
        });
        bottomLayout.setBottom(this,tabs,viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //切换到哪一页  导航条的文字发生改变
                switch (position)
                {
                    case 0:
                        getSupportActionBar().setTitle("综合");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        getSupportActionBar().setTitle("动弹");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        getSupportActionBar().setTitle("发现");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case 3:
//                        setTitle("我的");
                        //将toobar隐藏
                        toolbar.setVisibility(View.GONE);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
