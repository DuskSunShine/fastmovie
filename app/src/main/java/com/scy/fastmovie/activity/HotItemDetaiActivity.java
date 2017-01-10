package com.scy.fastmovie.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scy.fastmovie.R;
import com.scy.fastmovie.customviews.MyVideoView;
import com.scy.fastmovie.interfaces.ShuJu;

import java.io.IOException;

public class HotItemDetaiActivity extends AppCompatActivity {

    private MyVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_item_detai);
        ShuJu.activitys.add(this);

        videoView = (MyVideoView) findViewById(R.id.video);
        final MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoView.setMediaController(mediaController);
        Intent intent = getIntent();
        final String path = intent.getStringExtra("path");
        if (path!=null){
            try {
                videoView.setVideoPath(path);
            }catch (Exception e){
                Toast.makeText(HotItemDetaiActivity.this, "连接超时，请稍候重试", Toast.LENGTH_SHORT).show();
            }
        }
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("预告片");
        dialog.setMessage("玩命加载中\n请稍候。。。");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIcon(R.mipmap.holder);
        dialog.show();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                try{
                    videoView.requestFocus();
                    videoView.start();
                    mediaController.show();
                }catch (Exception e){
                    Toast.makeText(HotItemDetaiActivity.this, "该视频播放错误！", Toast.LENGTH_SHORT).show();
                }finally {
                    dialog.dismiss();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
