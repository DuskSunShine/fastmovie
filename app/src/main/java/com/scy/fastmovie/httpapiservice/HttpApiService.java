package com.scy.fastmovie.httpapiservice;

import com.scy.fastmovie.bean.DiscoverBean;

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
    
}
