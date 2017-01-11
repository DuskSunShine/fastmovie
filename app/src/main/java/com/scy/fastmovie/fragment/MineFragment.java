package com.scy.fastmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.FilmReview;
import com.scy.fastmovie.activity.HeadImageActivity;
import com.scy.fastmovie.activity.MyAchievement;
import com.scy.fastmovie.activity.MyCollectActivity;
import com.scy.fastmovie.activity.MyInfoActivity;
import com.scy.fastmovie.activity.MySettingActivity;
import com.scy.fastmovie.activity.OrderForGoodsActivity;
import com.scy.fastmovie.activity.PingJiaDetailActivity;
import com.scy.fastmovie.activity.SubjiectOfTalkActivity;
import com.scy.fastmovie.activity.ToTakeALookAt;
import com.scy.fastmovie.activity.WantToSeeActivity;
import com.scy.fastmovie.interfaces.MineDataCallBack;
import com.scy.fastmovie.utils.SPUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment  {


    private TextView loginnow;
    private Button wanttosee, totakelookat, filmreview, subjectoftalk;
    private RelativeLayout myorder, myinfo,mycollect,myachievement,mysetting;
    private RoundedImageView userhead;
    String spusername;
    private Button daipingjia;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        loginnow = (TextView) view.findViewById(R.id.login);
        wanttosee = (Button) view.findViewById(R.id.wanttosee);
        totakelookat = (Button) view.findViewById(R.id.kanguo);
        filmreview = (Button) view.findViewById(R.id.yingping);
        subjectoftalk = (Button) view.findViewById(R.id.huati);
        myorder = (RelativeLayout) view.findViewById(R.id.myorder);
        myinfo = (RelativeLayout) view.findViewById(R.id.myinfo);
        mycollect= (RelativeLayout) view.findViewById(R.id.mycollect);
        myachievement= (RelativeLayout) view.findViewById(R.id.myachievement);
        mysetting= (RelativeLayout) view.findViewById(R.id.mysetting);
        userhead = (RoundedImageView) view.findViewById(R.id.userhead);
        daipingjia = (Button) view.findViewById(R.id.daipingjia);
        loginnow.setText("立即登录");
        SharedPreferences sp = SPUtils.getSp(context);
        String userName = sp.getString("userName", "");
        if (!userName.equals("")){
            loginnow.setText(userName);
        }
        String userIcon = sp.getString("userIcon", "");
        if (!userIcon.equals("")){
            Glide.with(context).load(userIcon)
                    .placeholder(R.mipmap.adu)
                    .thumbnail(0.5f)
                    .skipMemoryCache(true)
                    .into(userhead);
        }else {
            userhead.setImageResource(R.mipmap.adu);
        }
        setonclicklistener();

        getusername();
        MySettingActivity.getBack(new MySettingActivity.Call() {
            @Override
            public void callBack() {
                userhead.setImageResource(R.mipmap.adu);
                loginnow.setText("立即登录");
            }
        });
        HeadImageActivity.setOnCallBackListener(new MineDataCallBack() {
            @Override
            public void setDataCallBack(Bitmap bitmap) {
                if (bitmap!=null){
                    userhead.setImageBitmap(bitmap);
                }
            }
        });
        return view;
    }




    private void getusername() {
//        SharedPreferences sp=getActivity().getSharedPreferences("regist", Context.MODE_PRIVATE);
        SharedPreferences sp = SPUtils.getSp(context);
        spusername = sp.getString("userName","");
        if (spusername.equals("")) {
            loginnow.setText("立即登录");
        }else {
            loginnow.setText(spusername);
        }
    }

    private void setonclicklistener() {
        mysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MySettingActivity.class);
                startActivity(intent);
            }
        });
        myachievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyAchievement.class);
                startActivity(intent);
            }
        });
        mycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyCollectActivity.class);
                startActivity(intent);
            }
        });
        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);*/
                if (!loginnow.getText().toString().equals("立即登录")){
                    Toast.makeText(context, "您已经登录过了", Toast.LENGTH_SHORT).show();
                }else {
                    ShareSDK.initSDK(context);
                    Platform platform=ShareSDK.getPlatform(QQ.NAME);
                    platform.setPlatformActionListener(new PlatformActionListener() {
                        @Override
                        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                            PlatformDb db = platform.getDb();
                            final String userName = db.getUserName();
                            final String userIcon = db.getUserIcon();
                            SharedPreferences sp = SPUtils.getSp(context);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("userName",userName);
                            edit.putString("userIcon",userIcon);
                            edit.apply();
                            Log.i("===",userName+"===name");
                            Log.i("===",userIcon+"===icon");
                            // TODO: 2017/1/11
                            userhead.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("===",userIcon+"==fffffff=icon");
                                    Glide.with(context).load(userIcon)
                                            .placeholder(R.mipmap.adu)
                                            .into(userhead);
                                }
                            },500);
                            userhead.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginnow.setText(userName);
                                }
                            },500);
                        }

                        @Override
                        public void onError(Platform platform, int i, Throwable throwable) {
                            Log.i("===",throwable.getMessage());
                        }

                        @Override
                        public void onCancel(Platform platform, int i) {
                            if (platform.isAuthValid()) {
                                platform.removeAccount();
                                SharedPreferences sp = SPUtils.getSp(context);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.clear();
                                edit.apply();
                            }
                        }
                    });
//                    platform.authorize();
                    platform.SSOSetting(false);
                    platform.showUser(null);
                }
            }
        });
        wanttosee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, WantToSeeActivity.class);
                startActivity(intent1);
            }
        });
        totakelookat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ToTakeALookAt.class);
                startActivity(intent);
            }
        });
        filmreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FilmReview.class);
                startActivity(intent);
            }
        });
        subjectoftalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubjiectOfTalkActivity.class);
                startActivity(intent);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderForGoodsActivity.class);
                startActivity(intent);
            }
        });
        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyInfoActivity.class);
                startActivity(intent);
            }
        });
        userhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HeadImageActivity.class);
                startActivity(intent);
            }
        });
        daipingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PingJiaDetailActivity.class));
            }
        });

    }



}

