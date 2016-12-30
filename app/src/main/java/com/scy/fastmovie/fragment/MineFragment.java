package com.scy.fastmovie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.FilmReview;
import com.scy.fastmovie.activity.LoginActivity;
import com.scy.fastmovie.activity.OrderForGoodsActivity;
import com.scy.fastmovie.activity.SubjiectOfTalkActivity;
import com.scy.fastmovie.activity.ToTakeALookAt;
import com.scy.fastmovie.activity.WantToSeeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment{


    private TextView loginnow;
    private Button wanttosee,totakelookat,filmreview,subjectoftalk;
    private RelativeLayout myorder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        loginnow = (TextView) view.findViewById(R.id.login);
        wanttosee= (Button) view.findViewById(R.id.wanttosee);
        totakelookat= (Button) view.findViewById(R.id.kanguo);
        filmreview= (Button) view.findViewById(R.id.yingping);
        subjectoftalk= (Button) view.findViewById(R.id.huati);
        myorder= (RelativeLayout) view.findViewById(R.id.myorder);

        setonclicklistener();

        return view;
    }

    private void setonclicklistener() {
        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        wanttosee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getContext(), WantToSeeActivity.class);
                startActivity(intent1);
            }
        });
        totakelookat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ToTakeALookAt.class);
                startActivity(intent);
            }
        });
        filmreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), FilmReview.class);
                startActivity(intent);
            }
        });
        subjectoftalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SubjiectOfTalkActivity.class);
                startActivity(intent);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), OrderForGoodsActivity.class);
                startActivity(intent);
            }
        });

    }

}
