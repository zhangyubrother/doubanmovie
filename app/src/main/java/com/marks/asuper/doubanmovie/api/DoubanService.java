package com.marks.asuper.doubanmovie.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2016/10/27.
 * Email ${EMAIL}
 */

public interface DoubanService {

    @GET("movie/top250")
    Observable<String> getMovieTop250(@Query("start") int start,
                                      @Query("count") int count);

}
