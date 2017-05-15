package com.example.yuer.myoschina.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.yuer.myoschina.Adapter.ZuixinRVAdapter;
import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.ZuixinResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.example.yuer.myoschina.widget.ImageBig;
import com.example.yuer.myoschina.widget.ImageLoad;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class BigImageActivity extends AppCompatActivity {

    int pageIndex=1;//初始是第一页
//    ImageView imageBig;
    ImageBig imageLoad;
    private String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

//        imageBig=(ImageView) findViewById(R.id.imageBig);
        imageLoad= (ImageBig) findViewById(R.id.imageBig);

        image = getIntent().getStringExtra("imageBig");
//        imageBig.setImageResource(image);
         imageLoad.setImages(image);
    }
}
