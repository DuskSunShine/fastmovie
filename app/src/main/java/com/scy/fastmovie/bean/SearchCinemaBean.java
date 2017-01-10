package com.scy.fastmovie.bean;

import java.util.List;

/**
 * Created by SCY on 2017/1/9 15:34.
 */

public class SearchCinemaBean {


    /**
     * data : [{"addr":"锦江区大业路6号财富广场4层D区","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":3,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hallType":["60帧厅"],"hasGift":0,"id":1550,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.652489,"lng":104.06969,"nm":"万达国际影城(财富店)","poiId":52820,"preferential":0,"referencePrice":"42.0","sell":true,"sellPrice":"28.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"青羊区青羊大道清江中路59号文化宫3楼","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":3.2,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hallType":["60帧厅","RealD厅"],"hasGift":0,"id":1530,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.670773,"lng":104.008995,"nm":"万达国际影城(金沙店)","poiId":771914,"preferential":0,"referencePrice":"42.0","sell":true,"sellPrice":"23.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"武侯区佳灵路7号红牌楼广场2幢2层","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":3.5,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hasGift":0,"id":1522,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.633331,"lng":104.02822,"nm":"万达国际影城(红牌楼店)","poiId":1439452,"preferential":0,"referencePrice":"42.0","sell":true,"sellPrice":"28.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"金牛区人民北路与一环路交汇处金牛万达广场4楼","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":4.2,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hallType":["RealD 6FL厅","IMAX厅"],"hasGift":0,"id":5315,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.685974,"lng":104.07504,"nm":"万达国际影城(人民北路店)","poiId":2393189,"preferential":0,"referencePrice":"48.0","sell":true,"sellPrice":"28.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"成华区府青路二段2号财富又一城5楼","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":5.5,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hallType":["60帧厅","RealD厅"],"hasGift":0,"id":14554,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.678524,"lng":104.09494,"nm":"万达国际影城(财富又一城店)","poiId":88261119,"preferential":0,"referencePrice":"0.0","sell":true,"sellPrice":"23.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"青羊区日月大道一段978号万达广场四楼","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":6.6,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hasGift":0,"id":16580,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.676752,"lng":103.974266,"nm":"万达影城(万达广场店)","poiId":117975439,"preferential":0,"referencePrice":"0.0","sell":true,"sellPrice":"28.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"成华区二环路东二段29号SM广场4层","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":6.8,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hasGift":0,"id":1543,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.668734,"lng":104.11144,"nm":"万达国际影城(SM店)","poiId":1577412,"preferential":0,"referencePrice":"24.0","sell":true,"sellPrice":"23.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"锦江区二环路东五段锦华路8号万达广场3层","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":7,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hallType":["IMAX厅"],"hasGift":0,"id":1529,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.620876,"lng":104.09572,"nm":"万达国际影城(锦华店)","poiId":1212091,"preferential":0,"referencePrice":"42.0","sell":true,"sellPrice":"28.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"双流县星空路二段万达广场四楼","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":13.1,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hasGift":0,"id":16627,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.597288,"lng":103.92722,"nm":"万达影城(双流万达广场店)","poiId":124675060,"preferential":0,"referencePrice":"0.0","sell":true,"sellPrice":"23.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}},{"addr":"郫县郫筒镇望丛东路139号4F-1","allowRefund":0,"allowRefundTime":0,"brdId":0,"closeStatus":0,"deal":0,"dealPrice":"0.0","distance":20.6,"endorse":0,"exclusive":0,"follow":0,"giftDesc":"","giftInfo":{"desc":"","hasGift":0,"isShow":0},"hallType":["60帧厅","RealD厅"],"hasGift":0,"id":15655,"imax":0,"isMerchantActivity":0,"isPlatformActivity":0,"lat":30.807377,"lng":103.90686,"nm":"蜀都万达电影城","poiId":91254643,"preferential":0,"referencePrice":"0.0","sell":true,"sellPrice":"28.0","showGiftTag":0,"snack":0,"type":0,"vipDesc":"","vipInfo":{"isSupport":0,"vipDesc":""}}]
     * total : 13
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 锦江区大业路6号财富广场4层D区
         * allowRefund : 0
         * allowRefundTime : 0
         * brdId : 0
         * closeStatus : 0
         * deal : 0
         * dealPrice : 0.0
         * distance : 3
         * endorse : 0
         * exclusive : 0
         * follow : 0
         * giftDesc : 
         * giftInfo : {"desc":"","hasGift":0,"isShow":0}
         * hallType : ["60帧厅"]
         * hasGift : 0
         * id : 1550
         * imax : 0
         * isMerchantActivity : 0
         * isPlatformActivity : 0
         * lat : 30.652489
         * lng : 104.06969
         * nm : 万达国际影城(财富店)
         * poiId : 52820
         * preferential : 0
         * referencePrice : 42.0
         * sell : true
         * sellPrice : 28.0
         * showGiftTag : 0
         * snack : 0
         * type : 0
         * vipDesc : 
         * vipInfo : {"isSupport":0,"vipDesc":""}
         */

        private String addr;
        private String dealPrice;
        private double distance;
        private int endorse;
        private int exclusive;
        private int follow;
        private String giftDesc;
        private int hasGift;
        private int id;
        private int imax;
        private int isMerchantActivity;
        private int isPlatformActivity;
        private String nm;
        private int poiId;
        private int preferential;
        private String referencePrice;
        private String sellPrice;
        private int showGiftTag;
        private int snack;
        private int type;
        private String vipDesc;
        private List<String> hallType;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getDealPrice() {
            return dealPrice;
        }

        public void setDealPrice(String dealPrice) {
            this.dealPrice = dealPrice;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getEndorse() {
            return endorse;
        }

        public void setEndorse(int endorse) {
            this.endorse = endorse;
        }

        public int getExclusive() {
            return exclusive;
        }

        public void setExclusive(int exclusive) {
            this.exclusive = exclusive;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public String getGiftDesc() {
            return giftDesc;
        }

        public void setGiftDesc(String giftDesc) {
            this.giftDesc = giftDesc;
        }

        public int getHasGift() {
            return hasGift;
        }

        public void setHasGift(int hasGift) {
            this.hasGift = hasGift;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImax() {
            return imax;
        }

        public void setImax(int imax) {
            this.imax = imax;
        }

        public int getIsMerchantActivity() {
            return isMerchantActivity;
        }

        public void setIsMerchantActivity(int isMerchantActivity) {
            this.isMerchantActivity = isMerchantActivity;
        }

        public int getIsPlatformActivity() {
            return isPlatformActivity;
        }

        public void setIsPlatformActivity(int isPlatformActivity) {
            this.isPlatformActivity = isPlatformActivity;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public int getPoiId() {
            return poiId;
        }

        public void setPoiId(int poiId) {
            this.poiId = poiId;
        }

        public int getPreferential() {
            return preferential;
        }

        public void setPreferential(int preferential) {
            this.preferential = preferential;
        }

        public String getReferencePrice() {
            return referencePrice;
        }

        public void setReferencePrice(String referencePrice) {
            this.referencePrice = referencePrice;
        }

        public String getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(String sellPrice) {
            this.sellPrice = sellPrice;
        }

        public int getShowGiftTag() {
            return showGiftTag;
        }

        public void setShowGiftTag(int showGiftTag) {
            this.showGiftTag = showGiftTag;
        }

        public int getSnack() {
            return snack;
        }

        public void setSnack(int snack) {
            this.snack = snack;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVipDesc() {
            return vipDesc;
        }

        public void setVipDesc(String vipDesc) {
            this.vipDesc = vipDesc;
        }

        public List<String> getHallType() {
            return hallType;
        }

        public void setHallType(List<String> hallType) {
            this.hallType = hallType;
        }

        public static class GiftInfoBean {
            /**
             * desc : 
             * hasGift : 0
             * isShow : 0
             */

            private String desc;
            private int hasGift;
            private int isShow;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getHasGift() {
                return hasGift;
            }

            public void setHasGift(int hasGift) {
                this.hasGift = hasGift;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }
        }

        public static class VipInfoBean {
            /**
             * isSupport : 0
             * vipDesc : 
             */

            private int isSupport;
            private String vipDesc;

            public int getIsSupport() {
                return isSupport;
            }

            public void setIsSupport(int isSupport) {
                this.isSupport = isSupport;
            }

            public String getVipDesc() {
                return vipDesc;
            }

            public void setVipDesc(String vipDesc) {
                this.vipDesc = vipDesc;
            }
        }
    }
}
