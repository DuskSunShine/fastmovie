package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

public class ToTakeALookAt extends AppCompatActivity {

    private RadioGroup group1,group2,group3;
    private RadioButton button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_take_alook_at);
        ShuJu.activitys.add(this);
        initdata();


    }

    private void initdata() {
        findviewbyid();
        button1= (RadioButton) group1.getChildAt(0);
        button1.setChecked(true);
        button1.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
        button2= (RadioButton) group2.getChildAt(0);
        button2.setChecked(true);
        button2.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
        button3= (RadioButton) group3.getChildAt(0);
        button3.setChecked(true);
        button3.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
        setonclicklistener();
    }

    private void setonclicklistener() {
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button = (RadioButton) group.getChildAt(i);
                    if (button.isChecked()) {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
                    } else {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shape);
                    }
                }
            }
        });
        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button = (RadioButton) group.getChildAt(i);
                    if (button.isChecked()) {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
                    } else {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shape);
                    }
                }
            }
        });
        group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button = (RadioButton) group.getChildAt(i);
                    if (button.isChecked()) {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
                    } else {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shape);
                    }
                }
            }
        });
    }

    private void findviewbyid() {
        group1 = (RadioGroup) findViewById(R.id.group1);
        group2 = (RadioGroup) findViewById(R.id.group2);
        group3 = (RadioGroup) findViewById(R.id.group3);
    }

    public void back(View view) {
        finish();
    }
}
