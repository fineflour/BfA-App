package org.bfa.bfa_app;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebviewActivity extends BaseActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String strUrl = getIntent().getExtras().getString("url");
        String strTitle = getIntent().getExtras().getString("title");
        WebView web_view = (WebView)findViewById(R.id.web_view);
        web_view.setWebViewClient(new WebViewClient());
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.loadUrl(strUrl);
        setTitle(strTitle);

    }
}
