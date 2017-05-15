package com.example.yuer.myoschina.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.TestActivity;
import com.example.yuer.myoschina.bean.ScrollImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yuer on 2017/5/4.
 */

public class ScrollImageLayout extends LinearLayout {

    private ViewPager mViewPager;
    private List<ImageView> images;//存储轮播的图片
    private List<ImageView> dots;//存储小圆点
    MyVPAdapter adapter;
    //但前页
    private int currentItem;
    //上一页
    private int oldPosition=0;


    private TextView tvTitle; //标题控件
    //线程池  进行定时循环任务
    private ScheduledExecutorService scheduledExecutorService;

    Context context;
    List<ScrollImageBean> imageList;
    private String[] titles;
    private int[] imageIds;

    public ScrollImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setImages(Context context, List<ScrollImageBean> imageList)
    {
        this.context=context;
        this.imageList=imageList;
        init();

    }

    private void init() {
        //初始化    控件以及数据
        View view= LayoutInflater.from(context).inflate(R.layout.layout_scrollimage,this,false);
        addView(view);
        LinearLayout ll_dots= (LinearLayout) view.findViewById(R.id.ll_dots);

        //两个数组存字符串
         titles=new String[imageList.size()];
         imageIds=new int[imageList.size()];
         dots=new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            titles[i]=imageList.get(i).getTitle();
            imageIds[i]=imageList.get(i).getImageId();
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(R.drawable.dot_green);
            LayoutParams params=new LayoutParams(40,40);
            dots.add(imageView);//小圆点加入集合 方便管理  变色
            ll_dots.addView(imageView,params);
        }



        mViewPager=(ViewPager) findViewById(R.id.vp);
        images=new ArrayList<>();
        for (int i=0;i<imageIds.length;i++)
        {
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(imageIds[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//图片拉伸
            images.add(imageView);
        }

        tvTitle= (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(titles[0]);

        adapter=new MyVPAdapter();
        mViewPager.setAdapter(adapter);

        mViewPager.setOffscreenPageLimit(images.size());//设置缓存页
        dots.get(0).setImageResource(R.drawable.dot_red);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //页面选中
                tvTitle.setText(titles[position]);//改成对应的标题
                dots.get(position).setImageResource(R.drawable.dot_red);   //小红点
                dots.get(oldPosition).setImageResource(R.drawable.dot_green); //小绿点
                oldPosition=position;
                currentItem=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

//                滑动状态  变化
//                Log.d("state", "onPageScrollStateChanged: "+state);
                switch (state)
                {
                    case 1:
                        //停止轮播
                        stop();
                        break;
                    case 2:
                        //自动切换和松手时都是2
                        if (scheduledExecutorService==null)
                        {
                            run();//如果不存在 开一下
                        }
                        break;
                }
            }
        });
    }
    public void run()
    {
        //开启循环任务   在子线程中执行
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),
                2,2, TimeUnit.SECONDS);//延迟两秒执行  间隔两秒
    }
    private class ViewPagerTask implements Runnable
    {

        @Override
        public void run() {
            //切页面(子线程中去修改ui 异步消息处理handler)  切到哪一页
            currentItem=(currentItem+1) % imageIds.length;
//            mViewPager.setCurrentItem(currentItem);  子线程不能处理主线程ui
            mHandler.sendEmptyMessage(0);

        }
    }
    private Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(currentItem);
        }
    };

    //停止轮播
    public void stop()
    {
        if (scheduledExecutorService!=null)
        {
            scheduledExecutorService.shutdown();
            scheduledExecutorService=null;
        }

    }
    private  class MyVPAdapter extends PagerAdapter
    {

        @Override
        public int getCount() {
            //要展示的页数  根据数组来
            return images.size();
        }

        //判断是否是同一页
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //如果滑动的图片超出缓存范围 会调用这个方法 将图片销毁（默认缓存3页）

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }

        //当要显示的图片可以进行缓存的时候会调用这个方法进行图片初始化
        //将要展示的imageView加入到viewGroup中作为返回值  即可
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }
    }

}
