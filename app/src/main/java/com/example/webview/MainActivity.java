package com.example.webview;

import android.app.usage.UsageEvents;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        //如果访问的页面中有JavaScript，则webview必须设置支持javascript
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        //访问网络地址
        mWebview.loadUrl("http://www.baidu.com/");
        //设置webview打开下一个网页而不是使用系统浏览器或第三方浏览器
        mWebview.setWebViewClient(new WebViewClient());
        //优先使用缓存
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //不使用缓存
      //  mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //判断页面加载过程
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                   // Toast.makeText(MainActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                }else{
                  //  Toast.makeText(MainActivity.this, "加载中", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//如果希望浏览的网页火腿而不是退出浏览器，需要webview覆盖URL加载，让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问的站点
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mWebview.canGoBack()){
                mWebview.goBack();//网页返回键
            }else{
                System.exit(0);//系统退出键
            }
        }
        return  super.onKeyDown(keyCode,event);
    }
}
