package com.example.yuer.myoschina.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.ScrollImageBean;
import com.example.yuer.myoschina.widget.ScrollImageLayout;

import java.util.ArrayList;
import java.util.List;

public class Test2Activity extends AppCompatActivity {

    private ScrollImageLayout scrollImageLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        scrollImageLayout= (ScrollImageLayout) findViewById(R.id.scrollimage);
        List<ScrollImageBean> scrollImageBeanList=new ArrayList<>();
        scrollImageBeanList.add(new ScrollImageBean("aaaa",R.drawable.a1));
        scrollImageBeanList.add(new ScrollImageBean("trtrter",R.drawable.a2));
        scrollImageBeanList.add(new ScrollImageBean("reter",R.drawable.a3));
        scrollImageBeanList.add(new ScrollImageBean("ertr",R.drawable.a4));
        scrollImageBeanList.add(new ScrollImageBean("terwe",R.drawable.a5));

        scrollImageLayout.setImages(this,scrollImageBeanList);

    }

    @Override
    protected void onStart() {
        super.onStart();
        scrollImageLayout.run();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scrollImageLayout.stop();
    }
}
