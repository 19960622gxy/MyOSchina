package com.example.yuer.myoschina.fragment.faxian;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yuer.myoschina.Adapter.MaindongdanVPAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.KaiYuanRuanJianActivity;
import com.example.yuer.myoschina.activity.Test4Activity;
import com.example.yuer.myoschina.activity.YaoYiYaoActivity;
import com.example.yuer.myoschina.fragment.comprehensive.NewsFragment;
import com.example.yuer.myoschina.fragment.comprehensive.PostFragment;
import com.example.yuer.myoschina.fragment.dongdan.RemenFragment;
import com.example.yuer.myoschina.fragment.dongdan.ZuixinFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuer on 2017/5/3.
 */

public class FaXianFragment extends Fragment {
    private LinearLayout llKai; //开源软件
    private LinearLayout llSao; //扫一扫
    private LinearLayout llYao;//摇一摇


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //通过布局文件生成对应的view
        View view = inflater.inflate(R.layout.fragment_faxian,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //有了view，去操作
        //找控件
        llKai=(LinearLayout) view.findViewById(R.id.faxian_kaiyuanruanjian);
        llKai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), KaiYuanRuanJianActivity.class);
                startActivity(intent);
            }
        });




        llSao = (LinearLayout) view.findViewById(R.id.ll_sao);
        llSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                llSao.setBackgroundColor(Color.parseColor("#D0D0D0"));
                Intent intent=new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });

        llYao = (LinearLayout) view.findViewById(R.id.ll_yao);
        llYao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                llYao.setBackgroundColor(Color.parseColor("#D0D0D0"));
                Intent intent=new Intent(getContext(), YaoYiYaoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if (data!=null)
                {
                    Bundle bundle=data.getExtras();
                    if (bundle==null)
                    {
                        return; //为空不处理
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS)
                    {
                        //成功
                        String result=bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(getContext(),"解析结果"+result,Toast.LENGTH_SHORT).show();

                    }else{
                        //失败
                        Toast.makeText(getContext(),"解析失败！",Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}
