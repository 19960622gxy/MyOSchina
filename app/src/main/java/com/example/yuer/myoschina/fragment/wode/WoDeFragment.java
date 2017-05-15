package com.example.yuer.myoschina.fragment.wode;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.YaoYiYaoActivity;
import com.example.yuer.myoschina.bean.MessageEvent;
import com.example.yuer.myoschina.bean.MessageEventdongdan;
import com.example.yuer.myoschina.bean.MyInformationResponse;
import com.example.yuer.myoschina.bean.NewsResponse;
import com.example.yuer.myoschina.bean.UserResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Yuer on 2017/5/3.
 */

public class WoDeFragment extends Fragment {

    private static final String TAG = "WoDeFragment";
    private TextView tvDongDan,tvShouCang,tvGuangZhu,tvFenSi;
    private ImageView image,image1;

    private SimpleDraweeView touXiang;
    private TextView yongHuMing;
    private TextView jiFen;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //通过布局文件生成对应的view
        View view = inflater.inflate(R.layout.fragment_wode,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //有了view，去操作
        //找控件
        touXiang= (SimpleDraweeView) view.findViewById(R.id.user_touxiang);
        yongHuMing= (TextView) view.findViewById(R.id.user_yonghuming);
        jiFen= (TextView) view.findViewById(R.id.user_jifen);

        getUserData();
        getMyInformationData();



        tvDongDan = (TextView) view.findViewById(R.id.tv_dongdan);
        tvShouCang = (TextView) view.findViewById(R.id.tv_shoucang);
        tvGuangZhu = (TextView) view.findViewById(R.id.tv_guangzhu);
        tvFenSi = (TextView) view.findViewById(R.id.tv_fensi);

        image = (ImageView) view.findViewById(R.id.image_lvse);
        tvShouCang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setVisibility(View.INVISIBLE);

            }
        });


        image1 = (ImageView) view.findViewById(R.id.image_lvse1);
        tvDongDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image1.setVisibility(View.INVISIBLE);

            }
        });



    }

    private void getMyInformationData() {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.MY_INFORMATION)
                .tag(this)
                .params("access_token",access_token)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

//                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        MyInformationResponse informationResponse = gson.fromJson(s,MyInformationResponse.class);
                        tvShouCang.setText(informationResponse.getFavoriteCount()+"");
                        tvGuangZhu.setText(informationResponse.getFollowersCount()+"");
                        tvFenSi.setText(informationResponse.getFansCount()+"");

                    }
                });

    }


    private void getUserData() {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.USER)
                .tag(this)
                .params("access_token",access_token)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        UserResponse userResponse = gson.fromJson(s,UserResponse.class);
                        yongHuMing.setText(userResponse.getName());

                            ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
                            int color1=colorGenerator.getRandomColor();
                            Drawable textDrawable= TextDrawable.builder()
                                    .beginConfig()
                                    .width(70)
                                    .height(70)
                                    .toUpperCase()
                                    .endConfig()
                                    .buildRound(userResponse.getName().substring(0,1),color1);
                            touXiang.setImageDrawable(textDrawable);



                    }
                });



    }



    // This method will be called when a MessageEvent is posted
    @Subscribe
    public void onMessageEvent(MessageEvent event){
        //接收到消息后的处理事件  响应
//        Toast.makeText(getContext(),"已收藏", Toast.LENGTH_SHORT).show();
        image.setVisibility(View.VISIBLE);
       int a= Integer.parseInt(tvShouCang.getText().toString());
       a++;
       tvShouCang.setText(a+"");
    }
    // This method will be called when a MessageEvent is posted
    @Subscribe
    public void onMessageEventdongdan(MessageEventdongdan event){
        //接收到消息后的处理事件  响应
//        Toast.makeText(getContext(),"已收藏", Toast.LENGTH_SHORT).show();
        image1.setVisibility(View.VISIBLE);
        int b=Integer.parseInt(tvDongDan.getText().toString());
        b++;
        tvDongDan.setText(b+"");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册接收器
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
