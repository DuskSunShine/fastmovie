package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scy.fastmovie.R;
import com.scy.fastmovie.interfaces.ShuJu;

public class FilmReview extends AppCompatActivity {

    private RadioGroup group;
    private RadioButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_review);
        ShuJu.activitys.add(this);
        initdata();
    }

    private void initdata() {
        findviewbyid();
        button= (RadioButton) group.getChildAt(0);
        button.setChecked(true);
        button.setBackgroundResource(R.drawable.myfilmreview_shapetwo);
        setonclicklistener();
    }

    private void setonclicklistener() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button= (RadioButton) group.getChildAt(i);
                    if (button.isChecked()){
                        button.setBackgroundResource(R.drawable.myfilmreview_shapetwo);
                    }else {
                        button.setBackgroundResource(R.drawable.myfilmreview_shape);
                    }
                }
            }
        });
    }

    private void findviewbyid() {
        group = (RadioGroup) findViewById(R.id.group);
    }

    public void back(View view) {
        finish();
    }
}
