package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.scy.fastmovie.R;
import com.scy.fastmovie.baseurl.BaseUrl;

public class TodayTopTenActivity extends AppCompatActivity {

    
    private ImageButton btn_top_cancel;
    private WebView top_webView;
    private ProgressBar top_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_top_ten);
        btn_top_cancel = (ImageButton) findViewById(R.id.btn_top_cancel);
        top_webView = (WebView) findViewById(R.id.top_webView);
        top_progress = (ProgressBar) findViewById(R.id.top_progress);
        
        top_webView.loadUrl(BaseUrl.TOPTEN);
        
        top_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                top_progress.setProgress(newProgress);
                if (newProgress==100){
                    top_progress.setVisibility(View.GONE);
                }
            }
        });
        btn_top_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
        
    }
}
