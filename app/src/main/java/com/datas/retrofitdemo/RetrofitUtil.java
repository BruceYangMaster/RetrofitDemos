package com.datas.retrofitdemo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 2016/8/25.
 */
public class RetrofitUtil {

    private static Retrofit retrofit;
    private static ApiService apiService;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().connectTimeout(10 * 1000, TimeUnit.MILLISECONDS).build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }



    public static ApiService getService() {
        return apiService;
    }

    public static void requestOnFailure(Call call, final Callback callback){
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }


}
