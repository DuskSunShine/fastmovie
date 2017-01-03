package com.scy.fastmovie.bean;

import java.util.List;

/**
 * Created by SCY on 2017/1/3 15:20.
 */

public class SearchResultBean {

    /**
     * data : [{"list":[{"commentCount":10,"id":17938,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17938","source":"猫眼电影","title":"《神探夏洛克》有多火，收视810万比女王圣诞致辞还要高","type":4,"viewCount":1290},{"author":"B面影碟","commentCount":5,"id":122498,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=122498","source":"","title":"2016年最佳冷门片\u2014\u2014赴汤蹈火","type":4,"viewCount":253},{"author":"尚杉小唛","commentCount":0,"id":121473,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=121473","source":"","title":"枪火欲火，众生浮世","type":4,"viewCount":65},{"author":"林哥很拉风","commentCount":0,"id":121168,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=121168","source":"","title":"《罗曼蒂克消亡史》：旗袍枪火辉映的血色浪漫","type":4,"viewCount":35},{"commentCount":4,"id":17173,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17173","source":"猫眼电影","title":"《罗曼蒂克消亡史》杭州路演，浪漫枪火芭蕾看呆观众","type":4,"viewCount":659},{"author":"文慧园路三号","commentCount":55,"id":120235,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=120235","source":"","title":"这部爆火的日本动画，要给中国青春片们上一课","type":4,"viewCount":4571},{"author":"战台烽","commentCount":0,"id":119736,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=119736","source":"","title":"尔冬升林更新，三少爷的薪火相传","type":4,"viewCount":25},{"commentCount":2,"id":16981,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=16981","source":"猫眼电影","title":"《冲天火》吴彦祖领衔男神团搏命演绎硬派动作片","type":4,"viewCount":617},{"author":"女王港","commentCount":0,"id":119493,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=119493","source":"","title":"路人简评-《冲天火》","type":4,"viewCount":69},{"commentCount":4,"id":16944,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=16944","source":"猫眼电影","title":"《冲天火》口碑视频：点燃\u201c冬天里的一把火\u201d","type":4,"viewCount":465}],"total":160,"type":4}]
     * correction : 
     * correctionV2 : 
     * correctionType : -1
     * algotype : -1
     * facetList : []
     */

    private String correction;
    private String correctionV2;
    private int correctionType;
    private int algotype;
    private List<DataBean> data;
    private List<?> facetList;

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getCorrectionV2() {
        return correctionV2;
    }

    public void setCorrectionV2(String correctionV2) {
        this.correctionV2 = correctionV2;
    }

    public int getCorrectionType() {
        return correctionType;
    }

    public void setCorrectionType(int correctionType) {
        this.correctionType = correctionType;
    }

    public int getAlgotype() {
        return algotype;
    }

    public void setAlgotype(int algotype) {
        this.algotype = algotype;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getFacetList() {
        return facetList;
    }

    public void setFacetList(List<?> facetList) {
        this.facetList = facetList;
    }

    public static class DataBean {
        /**
         * list : [{"commentCount":10,"id":17938,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17938","source":"猫眼电影","title":"《神探夏洛克》有多火，收视810万比女王圣诞致辞还要高","type":4,"viewCount":1290},{"author":"B面影碟","commentCount":5,"id":122498,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=122498","source":"","title":"2016年最佳冷门片\u2014\u2014赴汤蹈火","type":4,"viewCount":253},{"author":"尚杉小唛","commentCount":0,"id":121473,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=121473","source":"","title":"枪火欲火，众生浮世","type":4,"viewCount":65},{"author":"林哥很拉风","commentCount":0,"id":121168,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=121168","source":"","title":"《罗曼蒂克消亡史》：旗袍枪火辉映的血色浪漫","type":4,"viewCount":35},{"commentCount":4,"id":17173,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=17173","source":"猫眼电影","title":"《罗曼蒂克消亡史》杭州路演，浪漫枪火芭蕾看呆观众","type":4,"viewCount":659},{"author":"文慧园路三号","commentCount":55,"id":120235,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=120235","source":"","title":"这部爆火的日本动画，要给中国青春片们上一课","type":4,"viewCount":4571},{"author":"战台烽","commentCount":0,"id":119736,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=119736","source":"","title":"尔冬升林更新，三少爷的薪火相传","type":4,"viewCount":25},{"commentCount":2,"id":16981,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=16981","source":"猫眼电影","title":"《冲天火》吴彦祖领衔男神团搏命演绎硬派动作片","type":4,"viewCount":617},{"author":"女王港","commentCount":0,"id":119493,"newsUrl":"meituanmovie://www.meituan.com/forum/postDetail?postID=119493","source":"","title":"路人简评-《冲天火》","type":4,"viewCount":69},{"commentCount":4,"id":16944,"newsUrl":"meituanmovie://www.meituan.com/forum/newsDetail?id=16944","source":"猫眼电影","title":"《冲天火》口碑视频：点燃\u201c冬天里的一把火\u201d","type":4,"viewCount":465}]
         * total : 160
         * type : 4
         */

        private int total;
        private int type;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * commentCount : 10
             * id : 17938
             * newsUrl : meituanmovie://www.meituan.com/forum/newsDetail?id=17938
             * source : 猫眼电影
             * title : 《神探夏洛克》有多火，收视810万比女王圣诞致辞还要高
             * type : 4
             * viewCount : 1290
             * author : B面影碟
             */

            private int commentCount;
            private int id;
            private String newsUrl;
            private String source;
            private String title;
            private int type;
            private int viewCount;
            private String author;

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNewsUrl() {
                return newsUrl;
            }

            public void setNewsUrl(String newsUrl) {
                this.newsUrl = newsUrl;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
