package com.scy.fastmovie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scy.fastmovie.R;
import com.scy.fastmovie.bean.DaoMaster;
import com.scy.fastmovie.bean.DaoSession;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.bean.MovieDaoBean;
import com.scy.fastmovie.bean.MovieDaoBeanDao;
import com.scy.fastmovie.bean.WaitFragmentBean;
import com.scy.fastmovie.customviews.MyTextView;
import com.scy.fastmovie.db.MovieDbManager;
import com.scy.fastmovie.fragment.WaitFragment;
import com.scy.fastmovie.interfaces.ShuJu;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class BuyActivity extends AppCompatActivity implements View.OnClickListener{

    private MovieDaoBeanDao beanDao;
    private ImageView img_add;
    private ImageView img_jian;
    private TextView tv_num;
    private TextView tv_price;
    private MyTextView tv_name;
    private ImageView img;
    private TextView tv_tag;
    private String tag;
    private HotFragmentBean.DataBean.HotBean movie;
    private String flag;
    WaitFragmentBean.DataBean.ComingBean movie2;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ShuJu.activitys.add(this);

        initDB();
        initViews();

        Intent intent = getIntent();
        tag = intent.getStringExtra("flag");
        flag = intent.getStringExtra("tag");
        if (flag.equals("hot")){
            movie = (HotFragmentBean.DataBean.HotBean) intent.getSerializableExtra("movie");
        }else if (flag.equals("wait")){
            movie2= (WaitFragmentBean.DataBean.ComingBean) intent.getSerializableExtra("movie");
        }

        if (tag.equals("想看")){
            linearLayout.setVisibility(View.INVISIBLE);
            img_add.setVisibility(View.INVISIBLE);
            img_jian.setVisibility(View.INVISIBLE);
            tv_num.setVisibility(View.INVISIBLE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            img_add.setVisibility(View.VISIBLE);
            img_jian.setVisibility(View.VISIBLE);
            tv_num.setVisibility(View.VISIBLE);
        }
        if (flag.equals("hot")){
            initDatas();
        }else {
            initDatas2();
        }
        setListener();
        Window window=getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha=0.9f;    //这句就是设置窗口里崆件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
    }

    private void setListener() {
        img_add.setOnClickListener(this);
        img_jian.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    private void initDatas() {
        Random random=new Random();
        int nextInt = random.nextInt(30);
        tv_price.setText(nextInt+" 元/张");
        tv_name.setText(movie.getNm());
        String[] split = movie.getImg().split("/w.h");
        Glide.with(this).load(split[0]+split[1])
                .placeholder(R.mipmap.holder)
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .into(img);
        tv_tag.setText(tag);
    }
    private void initDatas2() {
        Random random=new Random();
        int nextInt = random.nextInt(30);
        tv_price.setText(nextInt+" 元/张");
        tv_name.setText(movie2.getNm());
        String[] split = movie2.getImg().split("/w.h");
        Glide.with(this).load(split[0]+split[1])
                .placeholder(R.mipmap.holder)
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .into(img);
        tv_tag.setText(tag);
    }

    private void initViews() {
        img_add = (ImageView) findViewById(R.id.add);
        img_jian = (ImageView) findViewById(R.id.jian);
        tv_num = (TextView) findViewById(R.id.num);
        tv_price = (TextView) findViewById(R.id.price);
        tv_name = (MyTextView) findViewById(R.id.name);
        img = (ImageView) findViewById(R.id.img);
        tv_tag = (TextView) findViewById(R.id.tag);
        linearLayout = (LinearLayout) findViewById(R.id.liner);

    }

    private void initDB() {
        beanDao = MovieDbManager.getDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                int i = Integer.parseInt(tv_num.getText().toString());
                i++;
                if (i<4){
                    tv_num.setText(i+"");
                }else {
                    Toast.makeText(BuyActivity.this, "每人同场电影的票仅限购3张", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jian:
                int j = Integer.parseInt(tv_num.getText().toString());
                if (j==0){
                    Toast.makeText(BuyActivity.this, "已经为0了", Toast.LENGTH_SHORT).show();
                    return;
                }
                j--;
                tv_num.setText(j+"");
                break;
            case R.id.img:
                if (flag.equals("hot")){
                    Intent intent = new Intent(BuyActivity.this, HotItemDetaiActivity.class);
                    intent.putExtra("path",movie.getVideourl());
                    startActivity(intent);
                    BuyActivity.this.overridePendingTransition(0,0);
                }else {
                    Intent intent = new Intent(BuyActivity.this, HotItemDetaiActivity.class);
                    intent.putExtra("path",movie2.getVideourl());
                    startActivity(intent);
                    BuyActivity.this.overridePendingTransition(0,0);
                }
                break;
        }
    }

    public void onBack(View view) {
        finish();
        overridePendingTransition(0,0);
    }

    public void onSure(View view) {
        if (tag.equals("想看")){
            Toast.makeText(BuyActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tv_num.getText().toString().equals("0")){
            Toast.makeText(BuyActivity.this, "请先选购至少一张", Toast.LENGTH_SHORT).show();
            return;
        }
        if (flag.equals("hot")){
            String[] split = movie.getImg().split("/w.h");
            beanDao.insertOrReplace(new MovieDaoBean(movie.getId(),split[0]+split[1],movie.getNm(),
                    movie.getVideourl(),tv_price.getText().toString(),movie.getRt(),tv_num.getText().toString(),false,true));
        }else {
            String[] split = movie2.getImg().split("/w.h");
            beanDao.insertOrReplace(new MovieDaoBean(movie2.getId(),split[0]+split[1],movie2.getNm(),
                    movie2.getVideourl(),tv_price.getText().toString(),movie2.getRt(),tv_num.getText().toString(),false,true));
        }
        finish();
        overridePendingTransition(0,0);
        Toast.makeText(BuyActivity.this, "恭喜购买成功", Toast.LENGTH_SHORT).show();
    }
}
