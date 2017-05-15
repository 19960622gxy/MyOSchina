package com.example.yuer.myoschina.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuer.myoschina.R;

public class Test3Activity extends AppCompatActivity {


    SensorManager sensorManager;//传感器的管理器  负责注册相关的传感器 监听对应的动作
    boolean isStart = false;
    ImageView shangY;
    ImageView xiaY;
    Animation mAnimationX,mAnimationS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        shangY= (ImageView) findViewById(R.id.shang_y);
        xiaY= (ImageView) findViewById(R.id.xia_y);


        //实例化
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        //通过sensorManager注册相关的传感器
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
                //传感器监听     传感器类型（加速度传感器）     接受传感器信息的频率

    }

    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float[] values=event.values;

            float x=values[0];   //x轴方向的加速度值
            float y=values[1];//y轴方向的加速度值
            float z=values[2];//z轴方向的加速度值
//            Log.d("d", "onSensorChanged: x:"+x+"-----y:"+y+"------z:"+z);


            int medumValues=20;
            if (Math.abs(x)>medumValues||Math.abs(y)>medumValues||Math.abs(z)>medumValues)
            {
//                Toast.makeText(Test3Activity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
                if (!isStart)
                {
                    yaoyiyao();
                }
            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //写摇一摇之后的事件
    private void yaoyiyao()
    {
        mAnimationX= AnimationUtils.loadAnimation(this,R.anim.translate_1);
        xiaY.startAnimation(mAnimationX);

        mAnimationS=AnimationUtils.loadAnimation(this,R.anim.translate_2);
        shangY.startAnimation(mAnimationS);

        //刚摇过  不要再显示
        isStart=true;
        Toast.makeText(Test3Activity.this,"摇了一摇",Toast.LENGTH_SHORT).show();
        //几秒之后    2秒之后才允许再摇
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isStart=false;
            }
        },2000);



    }
}
