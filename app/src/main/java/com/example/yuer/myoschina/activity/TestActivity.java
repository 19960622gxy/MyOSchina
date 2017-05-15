package com.example.yuer.myoschina.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.TabBean;
import com.example.yuer.myoschina.bean.ZuixinResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.example.yuer.myoschina.widget.BottomLayout;
import com.example.yuer.myoschina.widget.ImageLoad;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class TestActivity extends AppCompatActivity {
    Button btnLoad;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ll= (LinearLayout) findViewById(R.id.activity_test);

        btnLoad= (Button) findViewById(R.id.btn_image);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });
    }

    private void loadImage() {
        //请求数据,加载图片
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.TWEET_LIST)
                .tag(this)
                .params("access_token",access_token)
                .params("user",-1)
                .params("pageSize",20)
                .params("page",1)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: ");

     String images="https://www.oschina.net/uploads/space/"+
             "2017/0504/090321_jRgI_3104505_thumb.png,"+
             "2017/0504/090324_B7h4_3104505_thumb.png,"+
             "2017/0504/111655_Y1ZG_3037659_thumb.png,"+
             "2017/0504/111655_Y1ZG_3037659_thumb.png,"+
             "2017/0504/111655_Y1ZG_3037659_thumb.png";
//                          s=s.replaceAll("https://static.oschina.net/","https://www.oschina.net/");
//                        SimpleDraweeView sim=new SimpleDraweeView(TestActivity.this);
//                        sim.setImageURI("https://static.oschina.net/uploads/space/2017/0504/090321_jRgI_3104505_thumb.png");
//
//                        sim.setBackgroundColor(Color.RED);
                        ImageLoad imageLoad=new ImageLoad(TestActivity.this);
                        imageLoad.setImages(images);
                        ll.addView(imageLoad);



                    }
                });
    }
}
