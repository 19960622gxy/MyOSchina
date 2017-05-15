package com.example.yuer.myoschina.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yuer.myoschina.bean.TabBean;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Yuer on 2017/4/28.
 */

public class BottomLayout extends LinearLayout {
    List<TabBean> tabList;
    Context context;
    ViewPager viewPager;
    int midIndex;
    public BottomLayout(Context context) {
        super(context,null);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    public  void setBottom(Context context, List<TabBean> tabList, ViewPager viewPager){
        this.tabList = tabList;
        this.context = context;
        this.viewPager = viewPager;
        init();
    }

    private void init() {
        //初始化 bottomLayout 底部导航
        int mIndex=-1;
        for (int i = 0; i < tabList.size(); i++) {
            final TabBean tab = tabList.get(i);
            if (tab.getType()==1){
                mIndex +=1;
            }else {
                midIndex = i;
            }
            tab.setIndex(mIndex);

            final BottomTab bottomTab = new BottomTab(context,tab);
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight =1;
            layoutParams.gravity = Gravity.CENTER;
            addView(bottomTab,layoutParams);
            if (tab.getType()==1){
                bottomTab.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //切换
                        viewPager.setCurrentItem(tab.getIndex());
                    }
                });
            }else {
                bottomTab.setOnClickListener(mOnClickListener);
            }
            if (i==0){
                bottomTab.setSelected(true);//初始 选中第0项
            }

        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //先全设未选中
                for (int i = 0; i < getChildCount(); i++) {
                    getChildAt(i).setSelected(false);
                }
                //再将选中的页  设true
                //position  = 0 1     2 3
                if (position>=midIndex){   //midIndex=2
                    getChildAt(position+1).setSelected(true);
                }else {
                    getChildAt(position).setSelected(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    OnClickListener mOnClickListener;
    public  void setMidClickListener(OnClickListener onClickListener){
        //中间按钮的点击监听设置方法
        mOnClickListener =onClickListener;
    }
}