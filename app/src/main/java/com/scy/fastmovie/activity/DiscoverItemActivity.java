package com.scy.fastmovie.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class DiscoverItemActivity extends AppCompatActivity
        implements View.OnClickListener{

    private ImageButton btn_item_cancel,share;
    private ImageView star;
    private Button toComment;
    private TextView item_text;
    private WebView item_web;
    private ProgressBar item_progress;
    private EditText comment;
    private String title;
    private int imageCount=0;
    private boolean isChanged=false;
    private String title1;
    private String source;
    private int size;
    private int targetId,id;
    private Intent intent;
    private String images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_item);
        ShareSDK.initSDK(this);
        ShuJu.activitys.add(this);
        initViews();
        share.setOnClickListener(this);
        star.setOnClickListener(this);
        setListener();
        intent = getIntent();
            try {
                if (intent!=null){
                    targetId = intent.getIntExtra("targetId", -1);
                    size = intent.getIntExtra("size", -1);
                    source = intent.getStringExtra("source");
                    id = intent.getIntExtra("id", -1);
                    title = intent.getStringExtra("title");
                    title1 = intent.getStringExtra("title1");
                    images = intent.getStringExtra("images");
                    if (size==1||size==2){
                        item_text.setText(R.string.zixun);
                        item_web.loadUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                        return;
                    } if (size==3){
                        item_text.setText(R.string.topic);
                        item_web.loadUrl("http://m.maoyan.com/topic/"+targetId+"?_v_=yes");
                        return;
                    }
                    if (!source.equals("")){
                        item_text.setText(R.string.zixun);
                        item_web.loadUrl("http://m.maoyan.com/information/"+id+"?_v_=yes");
                    }else{
                        item_text.setText(R.string.topic);
                        item_web.loadUrl("http://m.maoyan.com/topic/"+id+"?_v_=yes");
                    }
                    imageCount = intent.getIntExtra("imageCount", -1);//imageCount可能没有
                    if (size==3&&imageCount>3){
                        item_text.setText(R.string.zixun);
                        item_web.loadUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                        return;
                    }
                }
            }catch (Exception e){

            }finally {
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
                item_web.setWebViewClient(new WebViewClient());
                btn_item_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        overridePendingTransition(0,0);
                    }
                });
            }
    }
    
    private void initViews(){
        btn_item_cancel = (ImageButton) findViewById(R.id.btn_item_cancel);
        item_text = (TextView) findViewById(R.id.item_text);
        star = (ImageView) findViewById(R.id.star);
        share= (ImageButton) findViewById(R.id.share);
        item_web = (WebView) findViewById(R.id.item_web);
        item_progress = (ProgressBar) findViewById(R.id.item_progress);
        comment=(EditText) findViewById(R.id.comment);
        toComment= (Button) findViewById(R.id.toComment);
        Drawable drawable = getResources().getDrawable(R.mipmap.comment);
        drawable.setBounds(0,0,40,40);
        comment.setCompoundDrawables(drawable,null,null,null);
    }
    private void setListener(){
        comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("==beforeTextChanged==",s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("==onTextChanged==",s.toString());
                if (s!=null) {
                    toComment.setBackgroundResource(R.mipmap.blue);
                }else{
                    toComment.setBackgroundResource(R.mipmap.gray);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("==afterTextChanged==",s.toString());
                if (s!=null) {
                    toComment.setBackgroundResource(R.mipmap.blue);
                }else{
                    toComment.setBackgroundResource(R.mipmap.gray);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.star:
                if (isChanged){
                    star.setImageResource(R.mipmap.clcstart);
                }else {
                    star.setImageResource(R.mipmap.star);
                }
                isChanged=!isChanged;
                break;
            case R.id.comment:
                toComment.setBackgroundResource(R.mipmap.gray);
                break;
            case R.id.toComment:

                break;
            case R.id.share:
                Share();
                break;
        }
    }

//    private void Comment(){
//
//    }
    private void Share(){
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(getString(R.string.app_name)+"分享");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        try{
            if (size==1||size==2){
                oks.setTitleUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                oks.setSiteUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                oks.setUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                oks.setImageUrl(images);
                return;
            }if (size==3){
                oks.setTitleUrl("http://m.maoyan.com/topic/"+targetId+"?_v_=yes");
                oks.setSiteUrl("http://m.maoyan.com/topic/"+targetId+"?_v_=yes");
                oks.setUrl("http://m.maoyan.com/topic/"+targetId+"?_v_=yes");
                oks.setImageUrl(images);
                return;
            }if (!source.equals("")){
                oks.setTitleUrl("http://m.maoyan.com/information/"+id+"?_v_=yes");
                oks.setSiteUrl("http://m.maoyan.com/information/"+id+"?_v_=yes");
                oks.setUrl("http://m.maoyan.com/information/"+id+"?_v_=yes");
            }else{
                oks.setTitleUrl("http://m.maoyan.com/topic/"+id+"?_v_=yes");
                oks.setSiteUrl("http://m.maoyan.com/topic/"+id+"?_v_=yes");
                oks.setUrl("http://m.maoyan.com/topic/"+id+"?_v_=yes");
            }
            imageCount = intent.getIntExtra("imageCount", -1);//imageCount可能没有
            if (size==3&&imageCount>3){
                oks.setTitleUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                oks.setSiteUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                oks.setUrl("http://m.maoyan.com/information/"+targetId+"?_v_=yes");
                oks.setImageUrl(images);
                return;
            }
        }catch (Exception e){

        }finally {
            // text是分享文本，所有平台都需要这个字段
            if (source!=null){
                oks.setText(title1);
            }else {
                oks.setText(title);
            }

// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
            //oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("添加评论");
// site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
            //oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
            oks.show(this);
        }
    }
}
