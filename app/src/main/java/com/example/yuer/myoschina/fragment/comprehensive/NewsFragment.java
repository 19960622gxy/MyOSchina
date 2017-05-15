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

import com.example.yuer.myoschina.Adapter.CompreNewsRVAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.NewsResponse;
import com.example.yuer.myoschina.bean.ScrollImageBean;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.example.yuer.myoschina.widget.ScrollImageLayout;
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
 * Created by Yuer on 2017/4/27.
 */

public class NewsFragment extends Fragment {
    List<NewsResponse.NewslistBean>  newsList;
    CompreNewsRVAdapter adapter;
    int pageIndex=1;//初始是第一页
    private ScrollImageLayout scrollImageLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_news,container,false);
        return v ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        SpringView springView= (SpringView) view.findViewById(R.id.spring_news);
        springView.setHeader(new DefaultHeader(getContext())); //设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉 刷新数据
                newsList.clear();//清空一次
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


        //构建新闻列表
        newsList = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_compre_news);
        adapter  = new CompreNewsRVAdapter(getContext(),newsList);


        scrollImageLayout=new ScrollImageLayout(getContext(),null);
        List<ScrollImageBean> scrollImageBeanList = new ArrayList<>();
        scrollImageBeanList.add(new ScrollImageBean("高手问答|人工智能在电商的作用",R.drawable.a1));
        scrollImageBeanList.add(new ScrollImageBean("源创会|上海南京站开始报名",R.drawable.a2));
        scrollImageBeanList.add(new ScrollImageBean("混程序员的江湖",R.drawable.a3));
        scrollImageBeanList.add(new ScrollImageBean("我为什么不在乎人工智能",R.drawable.a4));
        scrollImageBeanList.add(new ScrollImageBean("维护vscode的事情",R.drawable.a5));
        scrollImageLayout.setImages(getContext(),scrollImageBeanList);
        adapter.setHead(scrollImageLayout);



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        getData();
    }
        @Override
    public void onStart() {
        super.onStart();
        scrollImageLayout.run();

    }

    @Override
    public void onStop() {
        super.onStop();
        scrollImageLayout.stop();
    }
    private void getData() {
//        String token = getContext().getSharedPreferences("oschina",MODE_PRIVATE).getString("access_token","");
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.NEWS_LIST)
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
                        NewsResponse newsListResponse = gson.fromJson(s,NewsResponse.class);
                        List<NewsResponse.NewslistBean> data = newsListResponse.getNewslist();
                            newsList.addAll(data);
                            adapter.notifyDataSetChanged();
                    }
                });
    }
}
