package com.scy.fastmovie.bean;

import java.util.List;

/**
 * Created by TimiZhuo on 2017/1/4.
 */
public class WaitGridBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * boardId : 7
         * boardName : 热映口碑
         * movieId : 338349
         * movieImgs : ["http://p0.meituan.net/w.h/movie/018ceaadb90ac5c1b9ba33546d1fb746401981.jpg","http://p0.meituan.net/w.h/movie/910b2e6c7cb0da947d65ef5c33929eb9366676.jpg"]
         * movieName : 血战钢锯岭
         * url : meituanmovie://www.meituan.com/movieBoard/detail?id=7&boardType=7&title=热映口碑榜单
         */

        private int boardId;
        private String boardName;
        private int movieId;
        private String movieName;
        private String url;
        private List<String> movieImgs;

        public int getBoardId() {
            return boardId;
        }

        public void setBoardId(int boardId) {
            this.boardId = boardId;
        }

        public String getBoardName() {
            return boardName;
        }

        public void setBoardName(String boardName) {
            this.boardName = boardName;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getMovieImgs() {
            return movieImgs;
        }

        public void setMovieImgs(List<String> movieImgs) {
            this.movieImgs = movieImgs;
        }
    }
}
