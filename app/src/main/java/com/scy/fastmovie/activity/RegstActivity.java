package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

public class RegstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regst);
        ShuJu.activitys.add(this);
    }
}
