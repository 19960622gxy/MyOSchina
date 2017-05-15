package com.example.yuer.myoschina.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.yuer.myoschina.Adapter.DongDanDetailVPAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.DongDanDetailResponse;
import com.example.yuer.myoschina.fragment.dongdan.ZXDetailCommentFragment;
import com.example.yuer.myoschina.fragment.dongdan.ZuixinFragment;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.example.yuer.myoschina.widget.ImageLoad;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class DongdanDetailActivity extends AppCompatActivity {
    private static final String TAG = "DongdanDetailActivity";
    private int id;
    private int count;
    //获取实例
    private CircleImageView imageHead;
    private TextView tvName;
    private TextView tvBody;
    private TextView tvTime;
    private ImageView imageZan;//点赞图片

    ImageLoad imageLoad;

    private ActionBar actionBar;

    TabLayout tabLayout;
    ViewPager viewPager;
    DongDanDetailVPAdapter adapter;
    private String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongdan_detail);

        Toolbar toolbar=(Toolbar) findViewById(R.id.zxdetail_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }


        //实例化
        imageHead= (CircleImageView) findViewById(R.id.image_head);
        tvName= (TextView) findViewById(R.id.tv_dongdan_detail_name);
        tvBody= (TextView) findViewById(R.id.tv_dongdan_detail_content);
        tvTime= (TextView) findViewById(R.id.tv_dongdan_detial_time);

        imageLoad=(ImageLoad) findViewById(R.id.detail_imageLoad);



        imageZan= (ImageView) findViewById(R.id.image_dongdan_detail_zan);
        imageZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageZan.setImageResource(R.mipmap.ic_thumbup_actived);
            }
        });


        tabLayout= (TabLayout) findViewById(R.id.tab_dongdan_detail);
        viewPager= (ViewPager) findViewById(R.id.vp_dongdan_detail);

        //tabLayout和viewpager嵌套
        //使用viewpager需要设置adapater
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new ZuixinFragment());  //点赞的列表

        id = getIntent().getIntExtra("dongDanId",0); //0 是默认值
        count = getIntent().getIntExtra("commentCount",0);
        image = getIntent().getStringExtra("imagesmall");
        fragmentList.add(ZXDetailCommentFragment.newInstance(id,count));//评论的列表
//
        adapter=new DongDanDetailVPAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


//        Toast.makeText(this,"id="+id,Toast.LENGTH_SHORT).show();

        getData();

    }

    private void getData() {
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.DONGDAN_DETAIL)
                .tag(this)
                .params("access_token",access_token)
                .params("id",id)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: "+s);
//
                        s=s.replaceAll("https://static.oschina.net/","https://www.oschina.net/");
                        s=s.replaceAll("\"\""," {\"name\": \"default\",\"time\": \"1970-03-22 21:25:51\"}");
                        Gson gson=new Gson();
                        DongDanDetailResponse dongDanDetailResponse=gson.fromJson(s,DongDanDetailResponse.class);

//                        imageHead.setImageURI(dongDanDetailResponse.getPortrait());

                        if (dongDanDetailResponse.getPortrait().equals("https://www.oschina.net/img/portrait.gif"))
                        {
                            ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
                            int color1=colorGenerator.getRandomColor();
                            Drawable textDrawable= TextDrawable.builder()
                                    .beginConfig()
                                    .width(120)
                                    .height(120)
                                    .toUpperCase()
                                    .endConfig()
                                    .buildRound(dongDanDetailResponse.getAuthor().substring(0,1),color1);
                            imageHead.setImageDrawable(textDrawable);

                        }else
                        {
//                            imageHead.setImageURI(dongDanDetailResponse.getPortrait());
                            Picasso.with(DongdanDetailActivity.this).load(dongDanDetailResponse.getPortrait())
                                    .into(imageHead);
                        }






                        tvName.setText(dongDanDetailResponse.getAuthor());

                        String content=dongDanDetailResponse.getBody();
                        tvBody.setText(Html.fromHtml(content));
//                        tvBody.setText(dongDanDetailResponse.getBody());
//                        tvTime.setText(dongDanDetailResponse.getPubDate());

//
                        //清空子view
                        for (int i = 0; i <imageLoad.getChildCount() ; i++) {
                           imageLoad.removeView(imageLoad.getChildAt(i));
                        }
                        //加载图片
                        if (image== null) {
                            imageLoad.setVisibility(View.GONE);


                        } else {
                            imageLoad.setVisibility(View.VISIBLE);
                            imageLoad.setImages(image);
                        }

                        String pubTime = dongDanDetailResponse.getPubDate(); //转换成 xx小时前
                        SimpleDateFormat a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date=new Date();
                        Date dateNow=new Date();
                        try {
                            date=a.parse(pubTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Calendar calendar=Calendar.getInstance();
                        Calendar calendarNow=Calendar.getInstance();
                        calendar.setTime(date);
                        calendarNow.setTime(dateNow);
                        int years=calendarNow.get(Calendar.YEAR)-calendar.get(Calendar.YEAR);
                        int months=calendarNow.get(Calendar.MONTH)-calendar.get(Calendar.MONTH);
                        int days=calendarNow.get(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DAY_OF_MONTH);
                        int hours=calendarNow.get(Calendar.HOUR_OF_DAY)-calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes=calendarNow.get(Calendar.MINUTE)-calendar.get(Calendar.MINUTE);
                        int seconds=calendarNow.get(Calendar.SECOND)-calendar.get(Calendar.SECOND);
                        if (years>0)
                        {
                           tvTime.setText(years+"年前");
                        }
                        else
                        {
                            if (months>0)
                            {
                                tvTime.setText(months+"月前");
                            }
                            else
                            {
                                if (days>0)
                                {
                                    tvTime.setText(days+"日前");
                                }
                                else
                                {
                                    if (hours>0)
                                    {
                                        tvTime.setText(hours+"小时前");
                                    }
                                    else
                                    {
                                        if (minutes>0)
                                        {
                                            tvTime.setText(minutes+"分钟前");
                                        }
                                        else
                                        {
                                            tvTime.setText("刚刚");
                                        }
                                    }
                                }
                            }
                        }




                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.zxdetail_toolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.zxdetail_share:
//                Toast.makeText(this,"你点击了分享",Toast.LENGTH_SHORT).show();
                UMImage thumb = new UMImage(DongdanDetailActivity.this, R.mipmap.welcome);//资源文件
                UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("开源中国");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("不一样的中国");//描述
                //打开分享面板
                new ShareAction(DongdanDetailActivity.this)
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
                                Toast.makeText(DongdanDetailActivity.this,"取消分享",Toast.LENGTH_SHORT).show();
                            }
                        }).open();
                break;

            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }




}
