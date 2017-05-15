package com.example.yuer.myoschina.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Yuer on 2017/5/5.
 */

public class ImageBig extends LinearLayout {
    Context context;
    String[] images;
    public ImageBig(Context context) {
        this(context,null);

    }

    public ImageBig(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ImageBig(Context context, AttributeSet attrs, int defStyleAttr) {
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

        for (int i = 0; i <1; i++) {
            //加1行
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            //循环添加每行图片
            for (int j = 0;j <1; j++) {
                SimpleDraweeView sim = new SimpleDraweeView(context);

                String url;
                try {
                    url = images[j];
                    if (j >= 1) {
                        url = "https://www.oschina.net/uploads/space/" + url;
                    }
                } catch (Exception e) {
                    url = "";
                }
                //图片头  https://static.oschina.net/uploads/space/

                sim.setImageURI(url);
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 1250,1);//设置宽高
                linearLayout.addView(sim, params);
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
