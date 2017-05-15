package com.example.yuer.myoschina.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.myoschina.Adapter.CommentRVAdapter;
import com.example.yuer.myoschina.Adapter.ZuixinRVAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.NewsCommentResponse;
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

public class CommentListActivity extends AppCompatActivity {
    List<NewsCommentResponse.CommentListBean> commentList;
    private int id;
    private int count;
    CommentRVAdapter adapter;
    int pageIndex=1;//初始是第一页
    private ActionBar actionBar;
    private TextView tvCommentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        id = getIntent().getIntExtra("newsId",0);
        count=getIntent().getIntExtra("commentCount",0);

        Toolbar toolbar=(Toolbar) findViewById(R.id.commentlist_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
//如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }

        tvCommentCount= (TextView) findViewById(R.id.newscommentCount);
        tvCommentCount.setText(count+"");

        SpringView springView= (SpringView) findViewById(R.id.spring_comment);
        springView.setHeader(new DefaultHeader(this)); //设置头布局
        springView.setFooter(new DefaultFooter(this));//设置尾布局
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
//                pageIndex++;
                if (pageIndex<2&&count<20)
                {
                    Toast.makeText(CommentListActivity.this,"没有更多数据了",Toast.LENGTH_SHORT).show();
                    commentList.clear();//清空一次
                    pageIndex=1;
                    getData(id);
                }
                else if(count>20){
                    pageIndex++;
                    getData(id);
                    if (count==count)
                    {
                        Toast.makeText(CommentListActivity.this,"没有更多数据了",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        //构建评论列表
        commentList = new ArrayList<>();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_comment);
        adapter  = new CommentRVAdapter(this,commentList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);



        getData(id);
    }

    private void getData(int id) {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.COMMENT_LIST)
                .tag(this)
                .params("id",id)
                .params("catalog",1)
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
                        s=s.replaceAll("https://static.oschina.net/","https://www.oschina.net/");
                        s=s.replaceAll("\"\""," {\"name\": \"default\",\"time\": \"1970-03-22 21:25:51\"}");
                        NewsCommentResponse newsCommentResponse = gson.fromJson(s,NewsCommentResponse.class);
                        List<NewsCommentResponse.CommentListBean> data=newsCommentResponse.getCommentList();//获取地址  才能将浏览器加载
                        if (data!=null){
                            commentList.addAll(data);
                            adapter.notifyDataSetChanged();
                        }


                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.commentlist_toolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
//            case R.id.news_comment:
////                Toast.makeText(this,"你点击了news_comment",Toast.LENGTH_SHORT).show();
//                if (count==0)
//                {
//                    Toast.makeText(this,"当前还没有评论",Toast.LENGTH_SHORT).show();
//                }
//                else if (count>0){
//                    Intent intent=new Intent(NewsDetailActivity.this,CommentListActivity.class);
//                    intent.putExtra("newsId",id);  //传递给下一个activity
//                    intent.putExtra("commentCount",count);
//                    startActivity(intent);
//                }
//
//                break;
//            case R.id.delete:
//                Toast.makeText(this,"你点击了Delete",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.settings:
//                Toast.makeText(this,"你点击了Settings",Toast.LENGTH_SHORT).show();
//                break;
            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }




}
