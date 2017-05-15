package com.example.yuer.myoschina.fragment.faxian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuer.myoschina.Adapter.KaiYuanRVAdapter;
import com.example.yuer.myoschina.Adapter.TuiJianRVAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.KaiYuanResponse;
import com.example.yuer.myoschina.bean.TuiJianResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Yuer on 2017/5/2.
 */

public class TuiJianFragment extends Fragment {
    List<TuiJianResponse.ProjectlistBean>  tuijianList;
//     KaiYuanRVAdapter adapter;
    TuiJianRVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_tuijian,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//
//
        //构建列表
        tuijianList = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_tuijian);
        adapter  = new TuiJianRVAdapter(getContext(),tuijianList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        getData();
    }

    private void getData() {
//        String token = getContext().getSharedPreferences("oschina",MODE_PRIVATE).getString("access_token","");

        String access_token= PreferencesUtils.getString("access_token");

        OkGo.get(OSChinaApi.PROJIECT_LIST)
                .tag(this)
                .params("access_token",access_token)
                .params("type","recommend")
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
//                        s=s.replaceAll("https://static.oschina.net/","https://www.oschina.net/");
//                        s = s.replaceAll("\"\""," {\"name\": \"default\",\"time\": \"1970-03-22 21:25:51\"}");
                        TuiJianResponse tuijianResponse = gson.fromJson(s,TuiJianResponse.class);
                       List<TuiJianResponse.ProjectlistBean> data =tuijianResponse.getProjectlist();
                        tuijianList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}
