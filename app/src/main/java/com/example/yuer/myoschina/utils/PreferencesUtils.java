package com.example.yuer.myoschina.utils;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.yuer.myoschina.App;

import static android.content.Context.MODE_PRIVATE;

/**SharePreferences 工具类
 * Created by Yuer on 2017/5/3.
 */

public class PreferencesUtils {
    public static  String PREFERENCE_NAME="oschina";
    public PreferencesUtils() {
    }

    //保存数据  存一个string键值对
    public static boolean putString(String key, String value)
    {
        //将token存到本地  -sharedpref
        SharedPreferences pref = App.getContext().getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor edit =  pref.edit();
        edit.putString(key,value);
              //保存
        return  edit.commit();
    }
    public static boolean putInt(String key, int value)
    {
        //将token存到本地  -sharedpref
        SharedPreferences pref = App.getContext().getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor edit =  pref.edit();
        edit.putInt(key,value);
        //保存
        return  edit.commit();
    }

    public static String getString(String key,@Nullable String defaultValue)
    {
        SharedPreferences sharedPreferences=App.getContext().getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        return sharedPreferences.getString(key,defaultValue);

    }
    public static String getString(String key)
    {
        return getString(key,null);

    }

    public static int getInt(String key, @Nullable int defaultValue)
    {
        SharedPreferences sharedPreferences=App.getContext().getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        return sharedPreferences.getInt(key,defaultValue);

    }
    public static  int getInt(String key)
    {
        return getInt(key,0);
    }

}
