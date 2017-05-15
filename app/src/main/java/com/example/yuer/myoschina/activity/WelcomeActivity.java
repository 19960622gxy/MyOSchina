package com.example.yuer.myoschina.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuer.myoschina.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome();
    }

    private void welcome() {
        //判断是否登陆过  计时(延迟)3s后执行对应的跳转

        //延迟3秒
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否登陆过  利用认证的时候的保存在本地的token判断
                String access_token=getSharedPreferences("oschina",MODE_PRIVATE)
                        .getString("access_token",null);
                if (access_token==null)
                {
                    //如果为空 就没有登陆过  跳转认证界面
                    Intent intent=new Intent(WelcomeActivity.this,AuthorActivity.class);
                    startActivity(intent);

                }
                else
                {
                    //不为空 登陆过 跳转主界面
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);

                }
                finish(); //按返回不会回到欢迎界面
            }
        }, 3000);



    }
}
