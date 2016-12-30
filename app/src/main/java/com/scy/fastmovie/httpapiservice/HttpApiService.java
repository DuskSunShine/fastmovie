package com.scy.fastmovie.httpapiservice;

import com.scy.fastmovie.bean.DiscoverBean;
import com.scy.fastmovie.bean.HotFragmentBean;

import retrofit2.http.GET;
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
    
}
