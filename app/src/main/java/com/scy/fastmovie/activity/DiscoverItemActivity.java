package com.scy.fastmovie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

public class DiscoverItemActivity extends AppCompatActivity {

    private ImageButton btn_item_cancel,star,share;
    private TextView item_text;
    private WebView item_web;
    private ProgressBar item_progress;
    private int imageCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_item);
        ShuJu.activitys.add(this);
        initViews();
        Intent intent = getIntent();
       
            if (intent!=null){
                int targetId = intent.getIntExtra("targetId", -1);
                int size = intent.getIntExtra("size", -1);
                try{
                     imageCount = intent.getIntExtra("imageCount", -1);//imageCount可能没有
                }catch (Exception e){
                    
                }
                if (size==1||size==2){
                    item_text.setText(R.string.zixun);
                    item_web.loadUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                    return;
                } if (size==3){
                    item_text.setText(R.string.topic);
                    item_web.loadUrl("http://m.maoyan.com/topic/"+targetId+"?_v_=yes");
                    return;
                } if (size==3&&imageCount>3){
                    item_text.setText(R.string.zixun);
                    item_web.loadUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                    return;
                }
                item_web.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        item_progress.setProgress(newProgress);
                        if (newProgress==100){
                            item_progress.setVisibility(View.GONE);
                        }
                    }
                });
            } 
      
     

        btn_item_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
        
    }
    
    private void initViews(){
        btn_item_cancel = (ImageButton) findViewById(R.id.btn_item_cancel);
        item_text = (TextView) findViewById(R.id.item_text);
        star = (ImageButton) findViewById(R.id.star);
        share= (ImageButton) findViewById(R.id.share);
        item_web = (WebView) findViewById(R.id.item_web);
        item_progress = (ProgressBar) findViewById(R.id.item_progress);
    }
}
