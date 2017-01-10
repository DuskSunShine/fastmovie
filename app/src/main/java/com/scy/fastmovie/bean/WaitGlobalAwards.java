package com.scy.fastmovie.bean;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/6.
 *
 */
public class WaitGlobalAwards {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * festSessionId : 4063
         * festivalId : 113
         * festivalName : 金球奖
         * heldDate : 2017-01-08
         * img : http://p0.meituan.net/w.h/movie/926108993cf43669d5986517112c94da149319.jpg
         * movieId : 367891
         * movieName : 赴汤蹈火
         * prizeName : 最佳剧情电影(提名)
         * sessionNum : 74
         */

        private int festSessionId;
        private int festivalId;
        private String festivalName;
        private String heldDate;
        private String img;
        private int movieId;
        private String movieName;
        private String prizeName;
        private int sessionNum;

        public int getFestSessionId() {
            return festSessionId;
        }

        public void setFestSessionId(int festSessionId) {
            this.festSessionId = festSessionId;
        }

        public int getFestivalId() {
            return festivalId;
        }

        public void setFestivalId(int festivalId) {
            this.festivalId = festivalId;
        }

        public String getFestivalName() {
            return festivalName;
        }

        public void setFestivalName(String festivalName) {
            this.festivalName = festivalName;
        }

        public String getHeldDate() {
            return heldDate;
        }

        public void setHeldDate(String heldDate) {
            this.heldDate = heldDate;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getPrizeName() {
            return prizeName;
        }

        public void setPrizeName(String prizeName) {
            this.prizeName = prizeName;
        }

        public int getSessionNum() {
            return sessionNum;
        }

        public void setSessionNum(int sessionNum) {
            this.sessionNum = sessionNum;
        }
    }
}
