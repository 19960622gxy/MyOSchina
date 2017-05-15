package com.example.yuer.myoschina.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yuer.myoschina.R;
import com.example.yuer.myoschina.bean.TokenResponse;
import com.example.yuer.myoschina.utils.OSChinaApi;
import com.example.yuer.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class AuthorActivity extends AppCompatActivity {

    WebView webView;
    String client_id="h69HLUVmUgD5warMzEXQ";
    String app_key=	"nJVKehvlTr4LBn9z6r35RAcdJaxIvjbA";
    String redirect_uri="http://www.oschina.net";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        /*
        * (A)：应用通过 浏览器 或 Webview 将用户引导到 OSChina 三方认证页面 上
              https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id={client_id}①&redirect_uri={redirect_uri}②
          (B)：用户对应用进行授权
          (C)：OSChina 认证服务器 通过 回调地址（redirect_uri）将 用户授权码 传递给 应用服务器 或者直接在 Webview 中跳转到携带 用户授权码的回调地址上，Webview 直接获取code即可（redirect_uri?code=abc&state=xyz）
          (D)：应用服务器 或 Webview 使用 oauth2_token API 向 OSChina 认证服务器发送 用户授权码 以及 回调地址
          (E)： OSChina 认证服务器返回 AccessToken
        * */
        webView= (WebView) findViewById(R.id.webview);
        String url = "https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id="
                +client_id+"&redirect_uri="+redirect_uri;
        webView.loadUrl(url);


        //配置webview
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //   http://www.oschina.net/?code=mXFcYg&state=
                //截取到code=后的内容    mXFcYg
                Log.d("", "onPageFinished: ");
                if (url.contains("http://www.oschina.net/?code=")){
                    String code = url.replace("http://www.oschina.net/?code=","");
                    code = code.split("&")[0];
                    Log.d("code", "onPageFinished: "+code);
                    //将code授权码 传给服务器 请求 token码
                    getToken(code);
                }
                Log.d("onPageFinished", "onPageFinished: "+url);
                super.onPageFinished(view, url);
            }
        });
    }

    private void getToken(String code) {

        OkGo.get(OSChinaApi.TOKEN) //请求地址
                .tag(this)
                .params("client_id",client_id)   //应用id
                .params("client_secret",app_key)  //应用秘钥
                .params("grant_type","authorization_code")   //请求方式
                .params("redirect_uri",redirect_uri)   //回调地址
                .params("code",code)  //授权码
                .params("dataType","json")  //返回值格式
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s", "onSuccess: ");
                        Gson gson = new Gson();
                        TokenResponse tokenResponse = gson.fromJson(s,TokenResponse.class);
                        Log.d("t", "onSuccess: "+tokenResponse.getAccess_token());

//                        //将token存到本地  -sharedpref
//                        SharedPreferences pref = getSharedPreferences("oschina",MODE_PRIVATE);
//                        SharedPreferences.Editor edit =  pref.edit();
//                        edit.putString("access_token",tokenResponse.getAccess_token());
//                        edit.putString("refresh_token",tokenResponse.getRefresh_token());
//                        edit.putInt("uid",tokenResponse.getUid());
//                        edit.putString("token_type",tokenResponse.getToken_type());
//                        edit.putInt("expires_in",tokenResponse.getExpires_in());
//                        edit.apply();       //保存
                        PreferencesUtils.putString("access_token",tokenResponse.getAccess_token());
                        PreferencesUtils.putString("refresh_token",tokenResponse.getRefresh_token());
                        PreferencesUtils.putInt("uid",tokenResponse.getUid());
                        PreferencesUtils.putString("token_type",tokenResponse.getToken_type());
                        PreferencesUtils.putInt("expires_in",tokenResponse.getExpires_in());


                        Intent intent = new Intent(AuthorActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.d("d", "onError: ");
                    }
                });


    }
}
