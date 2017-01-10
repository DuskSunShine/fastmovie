package com.scy.fastmovie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ShuJu.activitys.add(this);
    }

    public void regist(View view) {
        Intent intent=new Intent(LoginActivity.this,RegstActivity.class);
        startActivity(intent);
    }
}
