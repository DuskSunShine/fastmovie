package com.scy.fastmovie.bean;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/9.
 */
public class WaitYuGaoBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img : http://p1.meituan.net/movie/90af756c4c42dd379f374d0fb7120fc934893.jpg
         * movieId : 248935
         * movieName : 大闹天竺
         * name : 《大闹天竺》《有钱没钱回家过年》MV
         * originName : 《有钱没钱回家过年》MV
         * url : http://maoyan.meituan.net/movie/videos/854x48017542ad0aa9e4c2c8b848c1a46a136fb.mp4
         * videoId : 82905
         * wish : 187829
         */

        private String img;
        private int movieId;
        private String movieName;
        private String name;
        private String originName;
        private String url;
        private int videoId;
        private int wish;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getWish() {
            return wish;
        }

        public void setWish(int wish) {
            this.wish = wish;
        }
    }
}
