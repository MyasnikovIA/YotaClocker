package com.example.yotamodem.web_lib;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.yotamodem.MainActivity;


public class console {

    private long lastUpdate;
    private WebView webView;
    private MainActivity parentActivity;

    public console(MainActivity activity, WebView webView) {
        this.webView = webView;
        parentActivity = activity;
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Вывод консоли
     *
     * @param msg
     */
    @JavascriptInterface
    public void log(String msg) {
        Log.d("console.log", msg);
    }
    @JavascriptInterface
    public void error(String msg) {
        Log.d("console.log", msg);
    }

}
