package com.scy.fastmovie.bean;

/**
 * Created by TimiZhuo on 2017/1/11.
 */
public class DaiPingJiaBean {
    long id;
    private String img;
    private String video;
    private String name;
    private String price;
    private String num;

    public DaiPingJiaBean(String img, String video, String name, String num, String price) {
        this.img = img;
        this.video = video;
        this.name = name;
        this.num = num;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DaiPingJiaBean() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
