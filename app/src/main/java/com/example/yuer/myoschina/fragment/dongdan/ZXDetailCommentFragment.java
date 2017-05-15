package com.example.yuer.myoschina.fragment.dongdan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yuer.myoschina.Adapter.ZXDetailCommentRVAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.activity.CommentListActivity;
import com.example.yuer.myoschina.bean.ZXDetailCommentResponse;
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

/**
 * Created by Yuer on 2017/5/9.
 */

public class ZXDetailCommentFragment extends Fragment{
    private static final String TAG = "ZXDetailCommentFragment";
    private int id;
    private int count;
    List<ZXDetailCommentResponse.CommentListBean> commentList;
    ZXDetailCommentRVAdapter adapter;
    int pageIndex=1;//初始是第一页

    public static ZXDetailCommentFragment newInstance(int id,int count ) {
        ZXDetailCommentFragment fragment = new ZXDetailCommentFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("Count", count);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_zxdetailcomment,container,false);
        return v ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b  = getArguments();
        id = b.getInt("id");
        count=b.getInt("count");
        SpringView springView= (SpringView) view.findViewById(R.id.spring_zxdetailcomment);
        springView.setHeader(new DefaultHeader(getContext())); //设置头布局
        springView.setFooter(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉 刷新数据
                commentList.clear();//清空一次
                pageIndex=1;
                getData(id);
            }

            @Override
            public void onLoadmore() {
                //上拉 加载数据
                if (pageIndex<2&&count==0)
                {
                    Toast.makeText(getContext(),"没有更多数据了",Toast.LENGTH_SHORT).show();
                    commentList.clear();//清空一次
                    pageIndex=1;
                    getData(id);
                }
                else {
                    pageIndex++;
                    getData(id);
                }

            }
        });
//        //构建新闻列表
        commentList = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_dongdan_zxdetailcomment);
        adapter  = new ZXDetailCommentRVAdapter(getContext(),commentList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        getData(id);
    }

    private void getData(int id) {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.COMMENT_LIST)
                .tag(this)
                .params("id",id)
                .params("catalog",3)
                .params("access_token",access_token)
                .params("page",pageIndex)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //先断点调试  再解析
                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson = new Gson() ;
                        ZXDetailCommentResponse zxdetailCommentResponse = gson.fromJson(s,ZXDetailCommentResponse.class);
                        List<ZXDetailCommentResponse.CommentListBean> data=zxdetailCommentResponse.getCommentList();//获取地址  才能将浏览器加载
                        if (data!=null){
                            commentList.addAll(data);
                            adapter.notifyDataSetChanged();
                        }


                    }
                });
    }
}
