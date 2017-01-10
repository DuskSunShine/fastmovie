package com.scy.fastmovie.httpapiservice;

import com.scy.fastmovie.bean.BannerBean;
import com.scy.fastmovie.bean.DiscoverBean;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.bean.SeekBean;
import com.scy.fastmovie.bean.SearchCinemaBean;
import com.scy.fastmovie.bean.SearchResultBean;
import com.scy.fastmovie.bean.WaitFragmentBean;
import com.scy.fastmovie.bean.WaitGlobalAwards;
import com.scy.fastmovie.bean.WaitGridBean;
import com.scy.fastmovie.bean.WaitMostBean;
import com.scy.fastmovie.bean.WaitYuGaoBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SCY on 2016/12/29 9:27.
 */

public interface HttpApiService {
    //发现列表
    @GET("sns/v5/feed.json?")
    Observable<DiscoverBean> getDiscoverData(
            @Query("offset") String offset,
            @Query("limit") String limit
    );
    @GET("mmdb/movie/v3/list/hot.json?")
    Observable<HotFragmentBean>getHotData(
            @Query("__reqTraceID")String __reqTraceID,@Query("__skck")String __skck,
            @Query("__skcy")String __skcy,@Query("__skno")String __skno,@Query("__skts")String __skts,
            @Query("__skua")String __skua,@Query("ci")String ci,@Query("dModel")String dModel,
            @Query("lat")String lat,@Query("limit")String limit,@Query("lng")String lng,@Query("movieBundleVersion")String movieBundleVersion,
            @Query("net")String net,@Query("refer")String refer,@Query("token")String token,@Query("utm_campaign")String utm_campaign,
            @Query("utm_content")String utm_content,@Query("utm_medium")String utm_medium,@Query("utm_source")String utm_source,
            @Query("utm_term")String utm_term,@Query("uuid")String uuid
    );
    //查找资讯
    //查找资讯 http://api.maoyan.com/mmdb/search/integrated/keyword/list.json?
    // almtype=1&keyword=水&stype=4&refer=0&iscorrected=true&limit=10&offset=0&ci=59
    @GET("mmdb/search/integrated/keyword/list.json?")
    Observable<SearchResultBean>getSearchResultData(
            @Query("almtype") String almtype,
            @Query("keyword") String keyword,
            @Query("stype") String stype,
            @Query("refer") String refer,
            @Query("iscorrected") String iscorrected,
            @Query("limit") String limit,
            @Query("offset") String offset,
            @Query("ci") String ci
    );
    //查找影院
    //http://api.maoyan.com/mmdb/search/cinema/keyword/list.json?
    // ctid=59&keyword=%E4%B8%87%E8%BE%BE&refer=1&referpage=0&limit=10&offset=0
    @GET("mmdb/search/cinema/keyword/list.json?")
    Observable<SearchCinemaBean> getSearchCinemaData(
            @Query("ctid") String ctid,
            @Query("keyword") String keyword,
            @Query("refer") String refer,
            @Query("referpage") String referpage,
            @Query("limit") String limit,
            @Query("offset") String offset,
            @Query("ci") String ci
    );
    @GET("api/v3/adverts?")
    Observable<BannerBean>getBannerData(
            @Query("__reqTraceID")String __reqTraceID,@Query("__skck")String __skck,@Query("__skcy")String __skcy,
            @Query("__skno")String __skno,@Query("__skts")String __skts,@Query("__skua")String __skua,
            @Query("app")String app,@Query("apptype")String apptype,@Query("category")String category,
            @Query("ci")String ci,@Query("cityid")String cityid,@Query("clienttp")String clienttp,@Query("devid")String devid,
            @Query("dModel")String dModel,@Query("lat")String lat,@Query("lng")String lng,@Query("movieBundleVersion")String movieBundleVersion,
            @Query("net")String net,@Query("new")String news,@Query("partner")String partner,
            @Query("refer")String refer,@Query("uid")String uid,@Query("utm_campaign")String utm_campaign,
            @Query("utm_content")String utm_content,@Query("utm_medium")String utm_medium,@Query("utm_source")String utm_source,
            @Query("utm_term")String utm_term,@Query("uuid")String uuid,@Query("version")String version
    );

    @GET("mmdb/movie/v2/list/rt/order/coming.json?ci=59&limit=12&token=yCbV9r7zv52i2GREfcA5QzgOxZ4AAAAAVwMAAASv4m3kDleIy_CBlG7s3oBfOURRpnznoBfbFWZP48AFbiQWN4bVxIjbVw-pGe-v6w&utm_campaign=AmovieBmovieCD100&movieBundleVersion=7701&utm_source=yingyonghui1-dy&utm_medium=android&utm_term=7.7.0&utm_content=866928026893953&net=255&dModel=BM002-G5&uuid=92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113&lat=30.662599&lng=104.040563&__reqTraceID=6432106778393259332&refer=%2FWelcome&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1482737690056&__skua=32bcf146c756ecefe7535b95816908e3&__skno=91c75473-da60-4e01-9deb-f83edbc2584f&__skcy=jqkeyC7AD4%2Fr8s4JrSk%2B%2FjdGd9c%3D")
    Observable<WaitFragmentBean>getWaitData();
    @GET("mmdb/search/movie/tag/types.json?")
    Observable<SeekBean>getSeekData(
            @Query("token")String token,@Query("utm_campaign")String utm_campaign,@Query("movieBundleVersion")String movieBundleVersion,
            @Query("utm_source")String utm_source,@Query("utm_medium")String utm_medium,@Query("utm_term")String utm_term,
            @Query("utm_content")String utm_content,@Query("ci")String ci,@Query("net")String net,@Query("dModel")String dModel,
            @Query("uuid")String uuid,@Query("refer")String refer
    );
    //资讯评论
    // http://api.maoyan.com/sns/news/comment.json?
    //ci=59&newsId=17947&text=heheh

    @GET("mmdb/movieboard/fixedboard/v1/hot/list.json?")
    Observable<WaitGridBean>getWaitGridData(
            @Query("utm_campaign")String utm_campaign,@Query("movieBundleVersion")String movieBundleVersion,@Query("utm_source")String utm_source,
            @Query("utm_medium")String utm_medium,@Query("utm_term")String utm_term,@Query("utm_content")String utm_content,
            @Query("ci")String ci,@Query("net")String net,@Query("dModel")String dModel,@Query("uuid")String uuid,@Query("refer")String refer

    );
    @GET("mmdb/movie/winning/film/2016-12-26/list.json?")
    Observable<WaitGlobalAwards>getWaitGlobalAwardsData(
            @Query("utm_campaign")String utm_campaign,@Query("movieBundleVersion")String movieBundleVersion,@Query("utm_source")String utm_source,
            @Query("utm_medium")String utm_medium,@Query("utm_term")String utm_term,@Query("utm_content")String utm_content,
            @Query("ci")String ci,@Query("net")String net,@Query("dModel")String dModel,@Query("uuid")String uuid,@Query("refer")String refer
    );
    @GET("mmdb/movie/lp/list.json?utm_campaign=AmovieBmovieCD100&movieBundleVersion=7701&utm_source=yingyonghui1-dy&utm_medium=android&utm_term=7.7.0&utm_content=866928026893953&ci=59&net=255&dModel=BM002-G5&uuid=92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113&refer=%2FWelcome")
    Observable<WaitYuGaoBean>getYuGaoData();

    @GET("mmdb/movie/v1/list/wish/order/coming.json?token=yCbV9r7zv52i2GREfcA5QzgOxZ4AAAAAVwMAAASv4m3kDleIy_CBlG7s3oBfOURRpnznoBfbFWZP48AFbiQWN4bVxIjbVw-pGe-v6w&offset=0&limit=50&ci=59&utm_campaign=AmovieBmovieCD100&movieBundleVersion=7701&utm_source=yingyonghui1-dy&utm_medium=android&utm_term=7.7.0&utm_content=866928026893953&net=255&dModel=BM002-G5&uuid=92D0E32E8154E517B48491A8CC0405BBF548E493CDDBD0CC6D5094ACBB73B113&refer=%2FWelcome")
    Observable<WaitMostBean>getMostData();
}
