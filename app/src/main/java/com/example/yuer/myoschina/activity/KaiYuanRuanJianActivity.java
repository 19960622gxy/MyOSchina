package com.example.yuer.myoschina.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yuer.myoschina.Adapter.KaiYuanVPAdapter;
import com.example.yuer.myoschina.Adapter.MainVPAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.fragment.comprehensive.BlogFragment;
import com.example.yuer.myoschina.fragment.comprehensive.NewsFragment;
import com.example.yuer.myoschina.fragment.comprehensive.PostFragment;
import com.example.yuer.myoschina.fragment.faxian.CnFragment;
import com.example.yuer.myoschina.fragment.faxian.FenLeiFragment;
import com.example.yuer.myoschina.fragment.faxian.TuiJianFragment;
import com.example.yuer.myoschina.fragment.faxian.ViewFragment;
import com.example.yuer.myoschina.fragment.faxian.ZuixinRuanjianFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

public class KaiYuanRuanJianActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_yuan_ruan_jian);

        Toolbar toolbar=(Toolbar) findViewById(R.id.kaiyuanruanjian_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }


        TabLayout tabLayout= (TabLayout) findViewById(R.id.tablayout_kaiyuan);
        ViewPager viewPager= (ViewPager) findViewById(R.id.vp_kaiyuan);
        //给viewPager设置adapter 重用MainVPAdapter  可以重建
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new FenLeiFragment());  //分类
        fragmentList.add(new TuiJianFragment());  //推荐
        fragmentList.add(new ZuixinRuanjianFragment());  //最新
        fragmentList.add(new ViewFragment());  //热门
        fragmentList.add(new CnFragment());  //国产


        KaiYuanVPAdapter adapter=new KaiYuanVPAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);//让tabLayout和viewPager两个有关联




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.kaiyuanruanjian_toolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }

}
