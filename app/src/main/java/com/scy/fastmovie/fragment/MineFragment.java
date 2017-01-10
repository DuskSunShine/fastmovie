package com.scy.fastmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.FilmReview;
import com.scy.fastmovie.activity.HeadImageActivity;
import com.scy.fastmovie.activity.LoginActivity;
import com.scy.fastmovie.activity.MainActivity;
import com.scy.fastmovie.activity.MyAchievement;
import com.scy.fastmovie.activity.MyCollectActivity;
import com.scy.fastmovie.activity.MyInfoActivity;
import com.scy.fastmovie.activity.MySettingActivity;
import com.scy.fastmovie.activity.OrderForGoodsActivity;
import com.scy.fastmovie.activity.SubjiectOfTalkActivity;
import com.scy.fastmovie.activity.ToTakeALookAt;
import com.scy.fastmovie.activity.WantToSeeActivity;
import com.scy.fastmovie.interfaces.MineDataCallBack;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment  {


    private TextView loginnow;
    private Button wanttosee, totakelookat, filmreview, subjectoftalk;
    private RelativeLayout myorder, myinfo,mycollect,myachievement,mysetting;
    private RoundedImageView userhead;
    private String spusername;

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

        setonclicklistener();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        HeadImageActivity.setOnCallBackListener(new MineDataCallBack() {
            @Override
            public void setDataCallBack(Bitmap bitmap) {
                if (bitmap!=null){
                    userhead.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getusername();
    }


    private void getusername() {
        SharedPreferences sp=getActivity().getSharedPreferences("regist", Context.MODE_PRIVATE);
        spusername = sp.getString("username","");
        if (spusername!="") {
            loginnow.setText(spusername);
        }else {
            loginnow.setText("立即登录");
        }
    }

    private void setonclicklistener() {
        mysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MySettingActivity.class);
                startActivity(intent);
            }
        });
        myachievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyAchievement.class);
                startActivity(intent);
            }
        });
        mycollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyCollectActivity.class);
                startActivity(intent);
            }
        });
        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        wanttosee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), WantToSeeActivity.class);
                startActivity(intent1);
            }
        });
        totakelookat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ToTakeALookAt.class);
                startActivity(intent);
            }
        });
        filmreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FilmReview.class);
                startActivity(intent);
            }
        });
        subjectoftalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SubjiectOfTalkActivity.class);
                startActivity(intent);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderForGoodsActivity.class);
                startActivity(intent);
            }
        });
        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyInfoActivity.class);
                startActivity(intent);
            }
        });
        userhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HeadImageActivity.class);
                startActivity(intent);
            }
        });

    }



}

