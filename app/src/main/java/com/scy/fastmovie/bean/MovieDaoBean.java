package com.scy.fastmovie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by TimiZhuo on 2017/1/10.
 */
@Entity
public class MovieDaoBean {
    @Id(autoincrement = true)
    long movie_id;

    String movie_img;
    String movie_name;
    String movie_video;
    String movie_price;
    String movie_date;
    String num;
    boolean movie_evaluate;
    boolean movie_buy;
    @Generated(hash = 1923005949)
    public MovieDaoBean(long movie_id, String movie_img, String movie_name,
            String movie_video, String movie_price, String movie_date, String num,
            boolean movie_evaluate, boolean movie_buy) {
        this.movie_id = movie_id;
        this.movie_img = movie_img;
        this.movie_name = movie_name;
        this.movie_video = movie_video;
        this.movie_price = movie_price;
        this.movie_date = movie_date;
        this.num = num;
        this.movie_evaluate = movie_evaluate;
        this.movie_buy = movie_buy;
    }
    @Generated(hash = 2098404493)
    public MovieDaoBean() {
    }
    public long getMovie_id() {
        return this.movie_id;
    }
    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
    }
    public String getMovie_img() {
        return this.movie_img;
    }
    public void setMovie_img(String movie_img) {
        this.movie_img = movie_img;
    }
    public String getMovie_name() {
        return this.movie_name;
    }
    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }
    public String getMovie_video() {
        return this.movie_video;
    }
    public void setMovie_video(String movie_video) {
        this.movie_video = movie_video;
    }
    public String getMovie_price() {
        return this.movie_price;
    }
    public void setMovie_price(String movie_price) {
        this.movie_price = movie_price;
    }
    public String getMovie_date() {
        return this.movie_date;
    }
    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public boolean getMovie_evaluate() {
        return this.movie_evaluate;
    }
    public void setMovie_evaluate(boolean movie_evaluate) {
        this.movie_evaluate = movie_evaluate;
    }
    public boolean getMovie_buy() {
        return this.movie_buy;
    }
    public void setMovie_buy(boolean movie_buy) {
        this.movie_buy = movie_buy;
    }
    
}
