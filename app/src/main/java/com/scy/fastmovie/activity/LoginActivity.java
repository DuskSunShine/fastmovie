package com.scy.fastmovie.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scy.fastmovie.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button login;
    private String spusername;
    private String sppassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initdata();
    }

    private void initdata() {
        getintentdata();
        findviewbyid();
        setonclicklistener();
    }

    private void getintentdata() {
     /*   Intent intent = getIntent();
        tag = intent.getIntExtra("tag",0);*/
        SharedPreferences sp = getSharedPreferences("regist", Context.MODE_PRIVATE);
        spusername = sp.getString("username", "");
        sppassword = sp.getString("password", "");
    }


    private void setonclicklistener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (username.getText().toString().equals("") && password.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                    } else if (username.getText().toString().equals(spusername)) {
                        if (password.getText().toString().equals(sppassword)) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "密码有误", Toast.LENGTH_SHORT).show();
                            password.setText("");
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "用户不存在，请先注册", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

    private void findviewbyid() {
        login = (Button) findViewById(R.id.login);
        username = (TextInputEditText) findViewById(R.id.ed_username);
        password = (TextInputEditText) findViewById(R.id.ed_password);
    }

    public void regist(View view) {
        Intent intent = new Intent(LoginActivity.this, RegstActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        username.setText(spusername);
        password.setText(sppassword);
    }

    /*public void login_success() {
        minefragment = new MineFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("username", spusername);
        minefragment.setArguments(bundle);
        transaction.replace(R.id.replace, minefragment);
        transaction.commit();

    }*/
}
