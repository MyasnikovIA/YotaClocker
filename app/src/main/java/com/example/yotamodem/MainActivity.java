package com.example.yotamodem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.yotamodem.lib.Keybord;
import com.example.yotamodem.lib.LockOrientation;
import com.example.yotamodem.web_lib.Android;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    protected PowerManager.WakeLock mWakeLock;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        new LockOrientation(this).lock();

        PowerManager pm2 = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm2.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "YotaModem::MainActivity");
        mWakeLock.acquire();

        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webView.setWebViewClient(new WebPageViewClient(webView));
        Android android = new Android(this, webView);
        webView.addJavascriptInterface(android, "Android");
        // webView.addJavascriptInterface(new console(this, webView), "console");
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onStart() {
        super.onStart();
        webView.loadUrl("https://welcome.yota.ru/phone-tethering?redirurl=https:%2F%2Fraw.githack.com%2FMyasnikovIA%2FWebSputnik%2Fmain%2Findex.html");
    }

    @Override
    public void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    // Java
    @Override
    public void onBackPressed() {
        webView.loadUrl("https://welcome.yota.ru/phone-tethering?redirurl=https:%2F%2Fraw.githack.com%2FMyasnikovIA%2FWebSputnik%2Fmain%2Findex.html");
    }
}