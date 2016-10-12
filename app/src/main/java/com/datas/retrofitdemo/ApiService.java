package com.datas.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 2016/8/24.
 */
public interface ApiService {

    @GET("{id}")
    Call<ResponseBody> getDataByGet(@Path("id") int id);

    @GET("{course_list}")
    Call<ResponseBody> getDataByGet(@Path("course_list") String course_list);

    @POST("content/home")
    Call<ResponseBody> getDataByPost(@Query("id") int id, @Query("tab") int tab);
}
