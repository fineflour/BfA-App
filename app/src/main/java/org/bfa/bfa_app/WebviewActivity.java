package org.bfa.bfa_app;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.basecamp.turbolinks.TurbolinksAdapter;
import com.basecamp.turbolinks.TurbolinksSession;
import com.basecamp.turbolinks.TurbolinksView;


public class WebviewActivity extends BaseActivity implements TurbolinksAdapter {

private  TurbolinksView turbolinksView;
    String strUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        strUrl = getIntent().getExtras().getString("url");
        String strTitle = getIntent().getExtras().getString("title");
        turbolinksView = (TurbolinksView)findViewById(R.id.turbolinks_view);

        TurbolinksSession.getDefault(this)
                .activity(this)
                .adapter(this)
                .view(turbolinksView)
                .visit(strUrl);

        //web_view.setWebViewClient(new WebViewClient());
        //web_view.getSettings().setJavaScriptEnabled(true);
        //web_view.loadUrl(strUrl);

        //getSupportActionBar().setTitle("Toolbar example");
        //toolbar.setSubtitle("Android-er.blogspot.com");
        toolbar.setLogo(R.drawable.ic_saved_order_space);
        setTitle(strTitle);

    }

    @Override
    public void onBackPressed() {
        TurbolinksSession.getDefault(this)
                .activity(this)
                .adapter(this)
                .view(turbolinksView)
                .visit(strUrl);
    }

    @Override
    public void onPageFinished() {

    }

    @Override
    public void onReceivedError(int errorCode) {

    }

    @Override
    public void pageInvalidated() {

    }

    @Override
    public void requestFailedWithStatusCode(int statusCode) {

    }

    @Override
    public void visitCompleted() {

    }

    @Override
    public void visitProposedToLocationWithAction(String location, String action) {

    }
}
