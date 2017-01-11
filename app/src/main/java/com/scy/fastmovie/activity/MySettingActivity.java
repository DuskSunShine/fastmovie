package com.scy.fastmovie.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;
import com.scy.fastmovie.utils.SPUtils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MySettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        ShuJu.activitys.add(this);
    }

    public void back(View view) {
        finish();
    }

    public void tuichu(View view) {
//        SharedPreferences sp=getSharedPreferences("regist", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=sp.edit();
//        editor.clear();
////        editor.remove("username");
//        editor.commit();
        ShareSDK.initSDK(MySettingActivity.this);
        Platform platform=ShareSDK.getPlatform(QQ.NAME);
        if (platform.isAuthValid()){
            platform.removeAccount();
            SharedPreferences sp = SPUtils.getSp(MySettingActivity.this);
            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.apply();
            Toast.makeText(MySettingActivity.this,"退出登录",Toast.LENGTH_SHORT).show();
            scall.callBack();
            finish();
        }else {
            Toast.makeText(MySettingActivity.this, "请先去登陆", Toast.LENGTH_SHORT).show();
        }

    }
    static Call scall;
    public static void getBack(Call call){
        scall=call;
    }
    public interface Call {
        void callBack();
    }
}
