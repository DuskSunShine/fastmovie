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

public class PiaoFangActivity extends AppCompatActivity {

    private ImageButton btn_piao_cancel;
    private WebView piao_webView;
    private ProgressBar piao_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piaofang);
        btn_piao_cancel = (ImageButton) findViewById(R.id.btn_piao_cancel);
        piao_webView = (WebView) findViewById(R.id.piao_webView);
        piao_progress = (ProgressBar) findViewById(R.id.piao_progress);
        
        piao_webView.loadUrl(BaseUrl.PIAO);
        piao_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                piao_progress.setProgress(newProgress);
                if (newProgress==100){
                    piao_progress.setVisibility(View.GONE);
                }
            }
        });
        piao_webView.setWebViewClient(new WebViewClient());
        btn_piao_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
    }
}
