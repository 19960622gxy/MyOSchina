package com.example.yuer.myoschina;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Yuer on 2017/4/27.
 */

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context=getApplicationContext();

        OkGo.init(this);//初始化 OkGo框架
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);//二维码框架



        //三方appkey
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106080409", "SeAzICbLaKokCIv9");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");

        UMShareAPI.get(this);
        Config.DEBUG = true;


    }

    public static  Context getContext()
    {
        return context;
    }
}