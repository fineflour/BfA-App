package org.bfa.bfa_app;

import android.os.Bundle;
import android.webkit.WebView;


public class WebviewActivity extends BaseActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView web_view;
        web_view = (WebView)findViewById(R.id.web_view);
        web_view.loadUrl("file:///android_asset/index.html");
    }
}
