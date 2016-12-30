package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scy.fastmovie.R;

public class WantToSeeActivity extends AppCompatActivity {

    private RadioGroup group1;
    private RadioGroup group2;
    RadioButton radiobutton;
    RadioButton radiobutton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_see);
        initdata();
        radiobutton= (RadioButton) group1.getChildAt(0);
        radiobutton.setChecked(true);
        radiobutton.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
        radiobutton2= (RadioButton) group2.getChildAt(0);
        radiobutton2.setChecked(true);
        radiobutton2.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
        setonclicklistenner();

    }

    private void setonclicklistenner() {
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button= (RadioButton) group.getChildAt(i);
                    if (button.isChecked()){
                        button.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
                    }else {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shape);
                    }
                }
            }
        });
        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button= (RadioButton) group.getChildAt(i);
                    if (button.isChecked()){
                        button.setBackgroundResource(R.drawable.wanttosee_group_shapetwo);
                    }else {
                        button.setBackgroundResource(R.drawable.wanttosee_group_shape);
                    }
                }
            }
        });
    }

    private void initdata() {
        findviewbyid();
    }

    private void findviewbyid() {
        group1 = (RadioGroup) findViewById(R.id.group1);
        group2 = (RadioGroup) findViewById(R.id.group2);
    }

    public void back(View view) {
        finish();
    }
}
