package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

/**
 * 点击查找资讯跳转后界面
 */
public class SearchNewsResultActivity extends AppCompatActivity {
    private Toolbar result_toolbar;
    private EditText search_edit_result;
    private Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news_result);
        ShuJu.activitys.add(this);
        initViews();
        setSupportActionBar(result_toolbar);
        setListener();
    }
    /**
     * 初始化布局
     */
    private void initViews(){
        result_toolbar= (Toolbar) findViewById(R.id.result_toolbar);
        search_edit_result= (EditText) findViewById(R.id.search_edit_result);
        btn_cancel= (Button) findViewById(R.id.btn_cancel);
    }

    /**
     * 控件监听
     */
    private void setListener(){
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edit_result.setText("");
                finish();
                overridePendingTransition(0,0);
            }
        });
    }
}
