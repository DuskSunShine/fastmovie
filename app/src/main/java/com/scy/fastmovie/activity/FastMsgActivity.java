package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.scy.fastmovie.R;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.interfaces.ShuJu;

public class FastMsgActivity extends AppCompatActivity {

    private ImageButton btn_fast_cancel;
    private WebView fast_webView;
    private ProgressBar fast_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_msg);
        ShuJu.activitys.add(this);
        btn_fast_cancel = (ImageButton) findViewById(R.id.btn_fast_cancel);
        fast_webView = (WebView) findViewById(R.id.fast_webView);
        fast_progress = (ProgressBar) findViewById(R.id.fast_progress);
        
        fast_webView.loadUrl(BaseUrl.FASTMSG);
        fast_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                fast_progress.setProgress(newProgress);
                if (newProgress==100){
                    fast_progress.setVisibility(View.GONE);
                }
            }
        });
        fast_webView.setWebViewClient(new WebViewClient());
        btn_fast_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
    }
}
