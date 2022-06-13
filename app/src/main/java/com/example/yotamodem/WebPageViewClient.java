package com.example.yotamodem;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.KeyEvent.KEYCODE_TAB;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPageViewClient extends WebViewClient {
    private WebView webWiew;

    public WebPageViewClient(WebView webWiew) {
        this.webWiew = webWiew;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    // Для старых устройств
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    StringBuffer preJsCode = new StringBuffer("" +
            "    document.querySelector(\".y-preloader-wrapper\").style.backgroundColor=\"black\";\n" +
            "    var box = document.createElement('div');\n" +
            "    box.innerHTML=\"<center> Регстрация в сети на 30 минут</center>\";\n" +
            "    box.style.position = 'fixed';\n" +
            "    box.style.top = '0px';\n" +
            "    box.style.left = '0px';\n" +
            "    box.style.width = '100%';\n" +
            "    box.style.height = '100%';\n" +
            "    box.style.backgroundColor = \"black\";\n" +
            "    document.getElementsByTagName(\"body\")[0].append(box);" +
            "    document.querySelector(\".main\").style.backgroundColor=\"black\";" +
            "    setTimeout(function(){  " +
            "        document.getElementsByTagName(\"y-hamburger\")[0].style.display=\"none\";" +
            "        document.getElementsByTagName(\"y-icon\")[0].style.display=\"none\";" +
            "    }, 1); \n" +
            "");

    StringBuffer sb = new StringBuffer("" +
            "    document.querySelector(\".y-button\").click(); \n" +
            "    setTimeout(function(){  \n" +
            "        document.querySelector(\".y-preloader-wrapper\").style.backgroundColor=\"black\";\n" +
            "    }, 1);" +
            "  ");

    @Override
    public void onPageFinished(WebView view, String url) {
        // после загрузки страницы, средствами JS модифицируем её
        if (url.equals(MainActivity.YotaUrl+"?redirurl="+MainActivity.SputnukUrl)) {
            webWiew.loadUrl("javascript:  " + preJsCode.toString() + "   ; "); // меняем CSS верстку страницы welcome.yota.ru/phone-tethering
            webWiew.loadUrl("javascript:  setTimeout(function(){ " + sb.toString() + "   }, 9000); "); // Находим кнопку по селектору , нажимем её и меняем фон прогрессора
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        new Thread() {
            @Override
            public void run() {
                // если не удалось загрузить страницу, тогда переходим на страницу регистрации на 30 минут
                webWiew.loadUrl(MainActivity.YotaUrl+"?redirurl="+MainActivity.SputnukUrl);
            }
        }.start();
    }

}
