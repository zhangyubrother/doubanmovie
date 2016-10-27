package com.marks.asuper.doubanmovie.services;

import com.marks.asuper.doubanmovie.model.DoubanBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface DoubanService {
    @FormUrlEncoded
    @POST("v2/movie/top250")
//    @GET("v2/movie/top250")
    Observable<DoubanBean> getDoubanBean(@Field("start") int start , @Field("count") int count);
}
