package com.example.yuer.myoschina.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.CommentPubResponse;
import com.example.yuer.myoschina.bean.MessageEvent;
import com.example.yuer.myoschina.bean.NewsCommentResponse;
import com.example.yuer.myoschina.bean.NewsDetailResponse;
import com.example.yuer.myoschina.bean.RemenResponse;
import com.example.yuer.myoschina.bean.ShoucangResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class NewsDetailActivity extends AppCompatActivity {


    private WebView webView;
    private Button btnSend;
    private ImageView newsdetial_share;
    private ImageView newsdetial_shoucang;
//    private CheckBox newsdetial_shoucang;
    private EditText etComment;
    private TextView commentCountDetail;// 详情页上方评论数

    private ActionBar actionBar;
    private int id;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);




        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上

        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }

        //拿控件
        webView = (WebView) findViewById(R.id.webview_newsdetail);
        btnSend=(Button) findViewById(R.id.btnSend);
        newsdetial_share= (ImageView) findViewById(R.id.newsdetial_share);
        newsdetial_shoucang= (ImageView) findViewById(R.id.newsdetial_shoucang);


        etComment=(EditText) findViewById(R.id.etComment);
        commentCountDetail=(TextView) findViewById(R.id.commentCount);


        //配置webview  AuthorActivity中有这部分代码
        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient());  //跳转只在本应用中进行




        //跳转到这里
        //id是int  注意对应  多打吐司提示
        id = getIntent().getIntExtra("newsId",0);
        count = getIntent().getIntExtra("commentCount", 0);
        commentCountDetail.setText(count+"");
//        Toast.makeText(this,"id="+id,Toast.LENGTH_SHORT).show();
        //请求数据
        getData(id);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mComment=etComment.getText().toString();
//                Toast.makeText(NewsDetailActivity.this,"你发送了"+ mComment,Toast.LENGTH_SHORT).show();
                sendData(mComment,id);
            }
        });

        newsdetial_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMImage thumb = new UMImage(NewsDetailActivity.this, R.mipmap.welcome);//资源文件
//                newsdetial_share.setImageResource(R.mipmap.btn_share_white_pressed);
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("开源中国");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("不一样的中国");//描述
                //打开分享面板
                new ShareAction(NewsDetailActivity.this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {
                                Toast.makeText(NewsDetailActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
                            }
                        }).open();
            }
        });


        newsdetial_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发一条消息
                newsdetial_shoucang.setImageResource(R.mipmap.ic_faved_normal);

                EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
                sendShouCangData(id);
            }
        });


    }

    private void sendShouCangData(int id) {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.FAVORITE_ADD)
                .tag(this)
                .params("access_token",access_token)
                .params("id",id)
                .params("type",4)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson=new Gson();
                        ShoucangResponse shoucangResponse=gson.fromJson(s,ShoucangResponse.class);
                        if (shoucangResponse.getError_description().equals("操作成功完成"))
                        {
                            Toast.makeText(NewsDetailActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(NewsDetailActivity.this,"收藏失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendData(String mComment, int id) {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.COMMENT_PUB)
                .tag(this)
                .params("access_token",access_token)
                .params("catalog",1)
                .params("id",id)
                .params("content",mComment)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

//                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson=new Gson();
                        CommentPubResponse commentPubResponse=gson.fromJson(s,CommentPubResponse.class);
                        if (commentPubResponse.getError_description().equals("操作成功完成"))
                        {
                            Toast.makeText(NewsDetailActivity.this,"已发送",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(NewsDetailActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getData(int id) {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.NEWS_DETAIL)
                .tag(this)
                .params("id",id)
                .params("access_token",access_token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //先断点调试  再解析
                        Log.d(TAG, "onSuccess: "+s);

                        Gson gson = new Gson() ;
                        NewsDetailResponse newsDetailResponse = gson.fromJson(s,NewsDetailResponse.class);
                        String url=newsDetailResponse.getUrl();//获取地址  才能将浏览器加载
                        webView.loadUrl(url);

                    }
                });
    }

    //分享的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.news_comment:
//                Toast.makeText(this,"你点击了news_comment",Toast.LENGTH_SHORT).show();

//                if (count==0)
//                {
//                    Toast.makeText(this,"当前还没有评论",Toast.LENGTH_SHORT).show();
//                }
//                else if (count>0){
                    Intent intent=new Intent(NewsDetailActivity.this,CommentListActivity.class);
                    intent.putExtra("newsId",id);  //传递给下一个activity
                    intent.putExtra("commentCount",count);
                    startActivity(intent);
//                }

                break;
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
