package com.example.yuer.myoschina.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Yuer on 2017/5/5.
 */

public class ImageLoad extends LinearLayout {
    Context context;
    String[] images;
    public ImageLoad(Context context) {
        this(context,null);

    }

    public ImageLoad(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ImageLoad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }
    public void setImages(String images)
    {
        this.images=images.split(",");
        init();
    }

    private void init() {
        //加载图片
        setOrientation(VERTICAL);
        int count=images.length;
        int lines;
        if (count%3==0)
        {
            lines=count/3;
        }else {
            lines=count/3+1;
        }
        for (int i = 0; i < lines; i++) {
            //加1行
            LinearLayout linearLayout=new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            //循环添加每行图片
            for (int j = i*3; j < (i+1)*3; j++) {
                SimpleDraweeView sim=new SimpleDraweeView(context);

                String url;
                try {
                    url=images[j];
                    if (j>=1)
                    {
                        url="https://www.oschina.net/uploads/space/"+url;
                    }
                }
                catch (Exception e)
                {
                    url="";
                }
                //图片头  https://static.oschina.net/uploads/space/

                sim.setImageURI(url);
                LayoutParams params=new LayoutParams(0,360,1);//设置宽高
                linearLayout.addView(sim,params);
            }
            addView(linearLayout);//将每一行添加到本布局
        }
//        if (count<=3)
//        {
//            lines=1;
//        }
//        else if(count<=6){
//            lines=2;
//
//        }else
//        {
//            lines=3;
//        }
    }
}
