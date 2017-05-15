package com.example.yuer.myoschina.activity;


import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.NewsResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class YaoYiYaoActivity extends AppCompatActivity {


    SensorManager sensorManager;//传感器的管理器  负责注册相关的传感器 监听对应的动作
    boolean isStart = false;
    ImageView yaoShake;
    TextView yaoText;
    TextView title;
    TextView time;
    Animation mAnimationS;

    private ActionBar actionBar;
    private ImageButton imLiPin,imZiXun;
    private TextView tvLiPin,tvZiXun,tvView;
    private LinearLayout llLiPin,llZiXun,llYaoZixun;

//    boolean isLiPin=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao_yi_yao);

        yaoShake= (ImageView) findViewById(R.id.yao_shake);
        yaoText= (TextView) findViewById(R.id.yao_text);
        title= (TextView) findViewById(R.id.yao_zixun_text);
        time=(TextView) findViewById(R.id.yao_zixun_time);

        //实例化
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        //通过sensorManager注册相关的传感器
        sensorManager.registerListener(sensorEventListenerZiXun,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        //传感器监听     传感器类型（加速度传感器）     接受传感器信息的频率




//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        llYaoZixun.setLayoutManager(layoutManager);
//        llYaoZixun.setAdapter(adapter);




        imLiPin= (ImageButton) findViewById(R.id.im_y_lipin);
//        imLiPin.setImageResource(R.mipmap.btn_shake_gift_actived);
        imZiXun= (ImageButton) findViewById(R.id.im_y_zixun);
        imZiXun.setImageResource(R.mipmap.btn_shake_info_actived);
        tvLiPin= (TextView) findViewById(R.id.tv_y_lipin);
//        tvLiPin.setTextColor(Color.parseColor("#000000"));
        tvZiXun= (TextView) findViewById(R.id.tv_y_zixun);
        tvZiXun.setTextColor(Color.parseColor("#000000"));

        llLiPin= (LinearLayout) findViewById(R.id.ll_lipin);
        llZiXun= (LinearLayout) findViewById(R.id.ll_zixun);
        tvView= (TextView) findViewById(R.id.tv_view_yaoyiyao);
        llYaoZixun= (LinearLayout) findViewById(R.id.ll_yao_zixun);

//                imLiPin.setImageResource(R.mipmap.btn_shake_gift_actived);
//
//                tvLiPin.setTextColor(Color.parseColor("#000000"));
//
//                imZiXun.setImageResource(R.mipmap.btn_shake_info_actived);
//                tvZiXun.setTextColor(Color.parseColor("#000000"));
//
        imLiPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                isLiPin=true;

                imZiXun.setImageResource(R.mipmap.btn_shake_info);
                tvZiXun.setTextColor(Color.parseColor("#D0D0D0"));

                imLiPin.setImageResource(R.mipmap.btn_shake_gift_actived);
                tvLiPin.setTextColor(Color.parseColor("#000000"));

                yaoText.setText("摇一摇抢礼品");
                yaoText.setTextColor(Color.parseColor("#989898"));


            }
        });
        imZiXun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                isLiPin=false;
                imLiPin.setImageResource(R.mipmap.btn_shake_gift);
                tvLiPin.setTextColor(Color.parseColor("#D0D0D0"));

                imZiXun.setImageResource(R.mipmap.btn_shake_info_actived);
                tvZiXun.setTextColor(Color.parseColor("#000000"));

                yaoText.setText("摇一摇获取资讯");
                yaoText.setTextColor(Color.parseColor("#989898"));
//
//                sensorManager.registerListener(sensorEventListenerZiXun,
//                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                        SensorManager.SENSOR_DELAY_NORMAL);
//                //传感器监听     传感器类型（加速度传感器）     接受传感器信息的频率


            }
        });






        Toolbar toolbar=(Toolbar) findViewById(R.id.yaoyiyao_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }



    }


    private SensorEventListener sensorEventListenerZiXun=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values=sensorEvent.values;

            float x=values[0];   //x轴方向的加速度值
            float y=values[1];//y轴方向的加速度值
            float z=values[2];//z轴方向的加速度值
//            Log.d("d", "onSensorChanged: x:"+x+"-----y:"+y+"------z:"+z);


            int medumValues=20;
//            if (isLiPin=true) {
            if (Math.abs(x) > medumValues || Math.abs(y) > medumValues || Math.abs(z) > medumValues) {
//                Toast.makeText(Test3Activity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
                if (!isStart) {
                    yZiXun();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


//    private SensorEventListener sensorEventListener=new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//
//            float[] values=event.values;
//
//            float x=values[0];   //x轴方向的加速度值
//            float y=values[1];//y轴方向的加速度值
//            float z=values[2];//z轴方向的加速度值
////            Log.d("d", "onSensorChanged: x:"+x+"-----y:"+y+"------z:"+z);
//
//
//            int medumValues=20;
////            if (isLiPin=true) {
//                if (Math.abs(x) > medumValues || Math.abs(y) > medumValues || Math.abs(z) > medumValues) {
////                Toast.makeText(Test3Activity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
//                    if (!isStart) {
//                        yLipin();
//                    }
//                }
////            }
//
//
//
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//        }
//    };


    //写摇一摇之后的事件
    private void yZiXun()
    {


        mAnimationS=AnimationUtils.loadAnimation(this,R.anim.translate_2);
        yaoShake.startAnimation(mAnimationS);

        yaoText.setText("摇一摇获取资讯");
        yaoText.setTextColor(Color.parseColor("#989898"));
        //刚摇过  不要再显示
        isStart=true;
        getData();
        tvView.setVisibility(View.VISIBLE);
        llYaoZixun.setVisibility(View.VISIBLE);

        Toast.makeText(YaoYiYaoActivity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
        //几秒之后    2秒之后才允许再摇
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isStart=false;
            }
        },2000);



    }

    private void getData() {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.NEWS_LIST)
                .tag(this)
                .params("access_token",access_token)
                .params("catalog",1)
                .params("page",1)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: ");
                        Gson gson = new Gson() ;
                        Random random=new Random();
                        int index=random.nextInt(19);
                        NewsResponse newsListResponse = gson.fromJson(s,NewsResponse.class);
                        List<NewsResponse.NewslistBean> data =   newsListResponse.getNewslist();
                        setData(data.get(index));
                    }
                });
    }
    private void setData(final NewsResponse.NewslistBean index){
        String pubTime = index.getPubDate();//转换成小时

//        String time1=pubTime.split(" ")[1];
        SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date dateNow = new Date();
        try {
            date = a.parse(pubTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();
        calendar.setTime(date);
        calendarNow.setTime(dateNow);
        int years = calendarNow.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        int months = calendarNow.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
        int days = calendarNow.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendarNow.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendarNow.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE);
        int seconds = calendarNow.get(Calendar.SECOND) - calendar.get(Calendar.SECOND);
        if (years > 0) {
            time.setText(years + "年前");
        } else {
            if (months > 0) {
                time.setText(months + "月前");
            } else {
                if (days > 0) {
                    time.setText(days + "日前");
                } else {
                    if (hours > 0) {
                        time.setText(hours + "小时前");
                    } else {
                        if (minutes > 0) {
                            time.setText(minutes + "秒前");
                        } else
                            time.setText("刚刚");
                    }
                }
            }
        }

        title.setText(index.getTitle());


        llYaoZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转对应的详情页
//                    Toast.makeText(context, "点击了"+news.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(YaoYiYaoActivity.this, NewsDetailActivity.class);
                intent.putExtra("newsId",index.getId());  //传递给下一个activity
                intent.putExtra("commentCount",index.getCommentCount());
                startActivity(intent);
            }
        });

    }



//    private void yLipin()
//    {
//
//
//        mAnimationS=AnimationUtils.loadAnimation(this,R.anim.translate_2);
//        yaoShake.startAnimation(mAnimationS);
//
//        yaoText.setText("抽奖活动已结束，请期待下次活动！");
//        yaoText.setTextColor(Color.parseColor("#989898"));
//        //刚摇过  不要再显示
//        isStart=true;
//        Toast.makeText(YaoYiYaoActivity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
//        //几秒之后    2秒之后才允许再摇
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                isStart=false;
//            }
//        },2000);
//    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.yaoyiyao_toolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }


}
