package com.scy.fastmovie.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SCY on 2017/1/9 17:37.
 */
@Entity
public class CinemaBean {
    @Id
    private long id;
    private String nm;
    private String sellPrice;
    private String addr;
    private String distance;
    @Generated(hash = 1943280884)
    public CinemaBean(long id, String nm, String sellPrice, String addr,
            String distance) {
        this.id = id;
        this.nm = nm;
        this.sellPrice = sellPrice;
        this.addr = addr;
        this.distance = distance;
    }
    @Generated(hash = 79877387)
    public CinemaBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNm() {
        return this.nm;
    }
    public void setNm(String nm) {
        this.nm = nm;
    }
    public String getSellPrice() {
        return this.sellPrice;
    }
    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }
    public String getAddr() {
        return this.addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getDistance() {
        return this.distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }

   
}
