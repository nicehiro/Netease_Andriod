package org.nicehiro.webviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.web_view);

        webView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new AppClient(), "AppClient");

        String data = "<html>\n" +
                "    <body>\n" +
                "        <a href=\"mailto:17826856902@163.com\">send mail</a>\n" +
                "<br>" +
                "        <a href=\"tel:17826856902\">call</a>\n" +
                "<br>" +
                "        <a href=\"smsto:17826856902\">send message</a>\n" +
                "    </body>\n" +
                "</html>";

//        webView.loadData(data, "text/html", null);
        webView.loadUrl("file:///android_asset/demo.html");
    }

    private class AppClient {
        @JavascriptInterface
        public void setActivity() {
//            Uri uri = Uri.parse("http://baidu.com");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            Intent intent = new Intent(MainActivity.this, DemoActivity.class);
            startActivity(intent);
        }

        @JavascriptInterface
        public void simpleNotification() {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
            builder.setContentTitle("最简通知")
                    .setContentText("这是通知内容")
                    .setSmallIcon(R.drawable.ic_status_bar_lollipop);
            NotificationManagerCompat notifyMgr = NotificationManagerCompat.from(MainActivity.this);
            notifyMgr.notify(1, builder.build());
        }
    }

    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//            boolean ret = super.shouldOverrideUrlLoading(view, url);
//
//            Uri uri = Uri.parse(url);
//
//            if (url.startsWith("mailto")) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//                ret = true;
//            } else if (url.startsWith("tel")) {
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//                startActivity(intent);
//                ret = true;
//            } else if (url.startsWith("smsto")) {
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                startActivity(intent);
//                ret = true;
//            }
//
//            return ret;
//        }


        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

            Uri uri = request.getUrl();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (request.getUrl().toString().startsWith("mailto")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else if (request.getUrl().toString().startsWith("smsto")) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    startActivity(intent);
                } else if (request.getUrl().toString().startsWith("tel")) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                }
            }

            return super.shouldInterceptRequest(view, request);
        }
    }
}
