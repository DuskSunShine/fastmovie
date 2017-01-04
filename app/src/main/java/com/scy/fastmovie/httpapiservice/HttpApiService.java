package com.scy.fastmovie.httpapiservice;

import com.scy.fastmovie.bean.BannerBean;
import com.scy.fastmovie.bean.DiscoverBean;
import com.scy.fastmovie.bean.HotFragmentBean;
import com.scy.fastmovie.bean.SearchResultBean;
import com.scy.fastmovie.bean.WaitFragmentBean;

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

    @GET("mmdb/movie/v2/list/rt/order/coming.json?")
    Observable<WaitFragmentBean>getWaitData(
            @Query("ci")String ci,@Query("limit")String limit,@Query("token")String token,@Query("utm_campaign")String utm_campaign,
            @Query("movieBundleVersion")String movieBundleVersion,@Query("utm_source")String utm_source,@Query("utm_medium")String utm_medium,
            @Query("utm_term")String utm_term,@Query("utm_content")String utm_content,@Query("net")String net,@Query("dModel")String dModel,
            @Query("uuid")String uuid,@Query("lat")String lat,@Query("lng")String lng,@Query("__reqTraceID")String __reqTraceID,
            @Query("refer")String refer,@Query("__skck")String __skck,@Query("__skts")String __skts,@Query("__skua")String __skua,
            @Query("__skno")String __skno,@Query("__skcy")String __skcy
    );
    //资讯评论
    // http://api.maoyan.com/sns/news/comment.json?
    //ci=59&newsId=17947&text=heheh
}
