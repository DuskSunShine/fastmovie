package com.scy.fastmovie.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.scy.fastmovie.R;

public class MySettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
    }

    public void back(View view) {
        finish();
    }

    public void tuichu(View view) {
        SharedPreferences sp=getSharedPreferences("regist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
//        editor.remove("username");
        editor.commit();
        Toast.makeText(MySettingActivity.this,"退出登录",Toast.LENGTH_SHORT).show();
        finish();

    }
}
