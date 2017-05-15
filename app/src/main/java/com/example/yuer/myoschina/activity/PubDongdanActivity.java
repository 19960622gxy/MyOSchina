package com.example.yuer.myoschina.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.CommentPubResponse;
import com.example.yuer.myoschina.bean.MessageEvent;
import com.example.yuer.myoschina.bean.MessageEventdongdan;
import com.example.yuer.myoschina.bean.PostResponse;
import com.example.yuer.myoschina.bean.PubDongdanResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class PubDongdanActivity extends AppCompatActivity {
    private EditText PubDongdan;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_dongdan);

        Toolbar toolbar=(Toolbar) findViewById(R.id.pubdongdan_toolbar);
        setSupportActionBar(toolbar);//将他设置为系统能够得知的actionbar的位置上
        //如果存在这样一个标题栏
        actionBar = getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_normal);//暂时先设置默认图片
        }


        PubDongdan= (EditText) findViewById(R.id.pub_dongdan);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //资源文件设置给对象
        getMenuInflater().inflate(R.menu.pubdondan_toolbar,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.sendDongdan:
//                Toast.makeText(this,"你点击了发送",Toast.LENGTH_SHORT).show();
                String mContent=PubDongdan.getText().toString();
                EventBus.getDefault().post(new MessageEventdongdan("Hello everyone!"));
                senddongdanData(mContent);

                break;

            case android.R.id.home:
                actionBar.setHomeAsUpIndicator(R.mipmap.btn_back_pressed);//暂时先设置默认图片
                finish();//退出当前页面
                break;
            default:
        }
        return true;
    }

    private void senddongdanData(String mContent) {
        String access_token= PreferencesUtils.getString("access_token");

        OkGo.get(OSChinaApi.TWEET_PUB)
                .tag(this)
                .params("access_token",access_token)
                .params("msg",mContent)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

//                        Log.d(TAG, "onSuccess: "+s);
                        Gson gson=new Gson();
                        PubDongdanResponse pubResponse=gson.fromJson(s,PubDongdanResponse.class);
                        if (pubResponse.getError_description().equals("操作成功完成"))
                        {
                            Toast.makeText(PubDongdanActivity.this,"已发送",Toast.LENGTH_SHORT).show();
                            PubDongdan.setText("");
                        }
                        else
                        {
                            Toast.makeText(PubDongdanActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
