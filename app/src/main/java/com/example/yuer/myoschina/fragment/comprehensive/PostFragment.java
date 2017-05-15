package com.example.yuer.myoschina.fragment.comprehensive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuer.myoschina.Adapter.PostRVAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.PostResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
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

public class PostFragment extends Fragment {
    List<PostResponse.PostListBean> postlist;
    PostRVAdapter adapter;
    int pageIndex=1;//初始是第一页

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_post,container,false);
        return v ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SpringView springView= (SpringView) view.findViewById(R.id.spring_post);
        springView.setHeader(new DefaultHeader(getContext())); //设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉 刷新数据
                postlist.clear();//清空一次
                pageIndex=1;
                getData();
            }

            @Override
            public void onLoadmore() {
                //上拉 加载数据
                pageIndex++;
                getData();

            }
        });




        //构建技术问答列表
        postlist = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_post);
        adapter  = new PostRVAdapter(getContext(),postlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        getData();
    }

    private void getData() {
//        String token = getContext().getSharedPreferences("oschina",MODE_PRIVATE).getString("access_token","");

        String access_token= PreferencesUtils.getString("access_token");

        OkGo.get(OSChinaApi.POST_LIST)
                .tag(this)
                .params("access_token",access_token)
                .params("catalog",1)
                .params("page",pageIndex)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: ");
                        Gson gson = new Gson() ;
                        s=s.replaceAll("https://static.oschina.net/","https://www.oschina.net/");
                        s = s.replaceAll("\"\""," {\"name\": \"default\",\"time\": \"1970-03-22 21:25:51\"}");
                        PostResponse postlistResponse = gson.fromJson(s,PostResponse.class);
                        List<PostResponse.PostListBean> data =postlistResponse.getPost_list();
                        postlist.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}
