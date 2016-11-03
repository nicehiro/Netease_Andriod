package org.nicehiro.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private WebView webView;
    private EditText inputEdit;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setMenuCallbacks(new MenuPresenter.Callback() {
            @Override
            public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            }

            @Override
            public boolean onOpenSubMenu(MenuBuilder subMenu) {
                toolbar.getMenu().findItem(R.id.menu_back).setEnabled(webView.canGoBack());
                toolbar.getMenu().findItem(R.id.menu_forward).setEnabled(webView.canGoForward());
                return false;
            }
        }, null);

        toolbar.inflateMenu(R.menu.menu_main);

        webView = (WebView) findViewById(R.id.content_web_view);
        inputEdit = (EditText) findViewById(R.id.net_input);
        webView.setWebViewClient(new BrowserWebViewClient());
        webView.setWebChromeClient(new BrowserChromeClient());
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        setWeb();
    }

    private void setWeb() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        settings.setLoadWithOverviewMode(true);

        String ua = settings.getUserAgentString();
        ua += "WebBrower/1.0";
        settings.setUserAgentString(ua);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_go:
                String url = inputEdit.getText().toString().trim();
                webView.loadUrl(url);
                break;
            case R.id.menu_refresh:
                webView.reload();
                break;
            case R.id.menu_back:
                webView.goBack();
                break;
            case R.id.menu_forward:
                webView.goForward();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class BrowserChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            progressBar.setVisibility(100 == newProgress ? View.GONE : View.VISIBLE);
        }
    }
}
