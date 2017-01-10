package com.scy.fastmovie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

public class RegstActivity extends AppCompatActivity {

    private Button regist;
    private TextInputEditText username;
    private TextInputEditText ed_password;
    private TextInputEditText ed_password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regst);
        ShuJu.activitys.add(this);
        initdata();
    }

    private void initdata() {
        findviewbyid();
        setonclicklistener();
    }

    private void setonclicklistener() {
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("") && ed_password.getText().toString().equals("")) {
                    Toast.makeText(RegstActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else if (ed_password.getText().toString().equals(ed_password2.getText().toString())) {
                    SharedPreferences sp = getSharedPreferences("regist", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", ed_password.getText().toString());
                    editor.commit();

                    Toast.makeText(RegstActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegstActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
//                    ed_password.setText("");
                    ed_password2.setText("");
                    Toast.makeText(RegstActivity.this, "密码有误，请重新输入", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void findviewbyid() {
        regist = (Button) findViewById(R.id.regist);
        username = (TextInputEditText) findViewById(R.id.ed_username);
        ed_password = (TextInputEditText) findViewById(R.id.ed_password);
        ed_password2 = (TextInputEditText) findViewById(R.id.ed_password2);

    }
}
