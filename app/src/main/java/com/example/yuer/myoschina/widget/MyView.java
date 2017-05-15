package com.example.yuer.myoschina.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.yuer.myoschina.R;

/**星轨自定义view
 * Created by Yuer on 2017/5/12.
 */

public class MyView extends View {
    Context context;
//    int radiam=90;//初始角度
        int radiams[]=new int[]{90,90,90,90,90};//初始角度

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        timeThread.start();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;

    }
    //动起来 每隔一定的时间  增加角度  刷新view
    private  Thread timeThread=new Thread()
    {
        @Override
        public void run() {
            try {

                while (true) {
                    Thread.sleep(150);
//                    radiam ++;
                    for (int i = 0; i < 5 ; i++) {

                        //第三第四是逆行
                        if (i==2||i==3)
                        {
                            radiams[i]-=i*2+1;
                        }
                        else {
                            radiams[i]+=i*2+1;
                        }
                    }

                    //发送
                    updateHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    //用handler刷新view
    private Handler updateHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            //刷新
            invalidate();
        }
    };

    /* *
     * 用来绘制自定义view
     *canvas 画布
     *
     * */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r=110;//155
        for (int i = 0; i < 5; i++) {
            //空心圆
            Paint paint = new Paint();  //画笔
            paint.setStyle(Paint.Style.STROKE);  //空心圆
            paint.setColor(getResources().getColor(R.color.colorWhite));
            paint.setAntiAlias(true);  //抗锯齿
            paint.setStrokeWidth(1f);  //borderWidth  画笔宽 画笔粗细
            //画圆
            canvas.drawCircle(getWidth() / 2, getHeight() / 2,r, paint);//Math.min(getHeight() / 2, getWidth() / 2) - 3f / 2


//        //实心圆   //默认为实心圆
//        Paint paintPoint = new Paint();  //画笔
//        paintPoint.setColor(getResources().getColor(R.color.colorWhite));
//        paintPoint.setAntiAlias(true);  //抗锯齿
//        //要求小实心圆在空心圆轨迹上跑
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2,100,paintPoint);

            //要求小实心圆在空心圆轨迹上跑
            //实心圆   //默认为实心圆
            Paint paintPoint = new Paint();  //画笔
            paintPoint.setColor(getResources().getColor(R.color.colorWhite));
            paintPoint.setAntiAlias(true);  //抗锯齿


            int x=(int) (getWidth()/2+r*Math.sin(Math.PI*radiams[i]/180));
            int y=(int) (getHeight()/2+r*Math.cos(Math.PI*radiams[i]/180));
            canvas.drawCircle(x, y,8,paintPoint);

//            r+=100+i*30;  //半径一次增加
            r+=50+i*30;
            //动起来 每隔一定的时间  增加角度  刷新view
        }















    }

    //用来测量该自定义view的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                                //   测量模式 宽           测量模式 高
        setMeasuredDimension(myMeasure(widthMeasureSpec), myMeasure(heightMeasureSpec));
    }

    //自定义测量模式
    private int myMeasure(int origin) {
        int result=300; //默认宽高
        //测量宽或者高
        //先获取测量模式
        int specMode=MeasureSpec.getMode(origin);
        //获取具体值
        int specSize=MeasureSpec.getSize(origin);
        if (specMode==MeasureSpec.EXACTLY)   //精准模式
        {
            result=specSize;
        }else if (specMode==MeasureSpec.AT_MOST){   //最大值模式  wrap_content

            result=Math.min(result,specSize);
        }
        return result;
    }

}
