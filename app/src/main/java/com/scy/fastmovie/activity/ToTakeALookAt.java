package com.scy.fastmovie.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.WantToSeehotAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.WantToSeeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import com.scy.fastmovie.interfaces.ShuJu;

public class ToTakeALookAt extends AppCompatActivity {

    private RadioGroup group1,group2,group3;
    private RadioButton button1,button2,button3;
    private RecyclerView recyclerview;
    private WantToSeeBean wantwosee;
    private List<WantToSeeBean.DataBean.HotBean> lists=new ArrayList<>();
    private WantToSeehotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_take_alook_at);
        ShuJu.activitys.add(this);
        initdata();


    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    setadapter();
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

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
        downloaddata();
        setadapter();
        setonclicklistener();
    }


    private void setadapter() {
        recyclerview.setLayoutManager(new LinearLayoutManager(ToTakeALookAt.this));
        adapter = new WantToSeehotAdapter(ToTakeALookAt.this,lists);
        recyclerview.setAdapter(adapter);

    }

    private void downloaddata() {
        OkHttpUtils.get().url(BaseUrl.HOT)
                .addParams("__reqTraceID", "-2534977814955072788")
                .addParams("__skck", "6a375bce8c66a0dc293860dfa83833ef")
                .addParams("__skcy", "gVtzEILPCkn+HM7gau8CxNPSc8w=")
                .addParams("__skno", "ae0ea14d-8eb9-4343-9972-8e4b78795040")
                .addParams("__skts", "1482719428398")
                .addParams("__skua", "32bcf146c756ecefe7535b95816908e3")
                .addParams("ci", "59")
                .addParams("dModel", "BM002-G5")
                .addParams("lat", "30.662584")
                .addParams("limit", "12")
                .addParams("lng", "104.040636")
                .addParams("movieBundleVersion", "7701")
                .addParams("net", "255")
                .addParams("refer", "/Welcome")
                .addParams("token", "yCbV9r7zv52i2GREfcA5QzgOxZ4AAAAAVwMAAASv4m3kDleIy_CBlG7s3oBfOURRpnznoBfbFWZP48AFbiQWN4bVxIjbVw-pGe-v6w")
                .addParams("utm_campaign", "AmovieBmovieCD100")
                .addParams("utm_content", "866928026893953")
                .addParams("utm_medium", "android")
                .addParams("utm_source", "yingyonghui1-dy")
                .addParams("utm_term", "7.7.0")
                .addParams("uuid", "92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ToTakeALookAt.this, "有问题", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        Toast.makeText(ToTakeALookAt.this, response, Toast.LENGTH_LONG).show();
                        Gson gson = new Gson();
                        wantwosee = gson.fromJson(response, WantToSeeBean.class);
                        lists=wantwosee.getData().getHot();

                        handler.sendEmptyMessage(100);
//                        Toast.makeText(ToTakeALookAt.this, "长度" + lists.size(), Toast.LENGTH_LONG).show();
                    }
                });
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
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    public void back(View view) {
        finish();
    }
}
