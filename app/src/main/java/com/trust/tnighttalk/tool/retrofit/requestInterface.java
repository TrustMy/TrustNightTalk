package com.trust.tnighttalk.tool.retrofit;

import com.trust.tnighttalk.tool.kttest.Test;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Trust on 2018/2/5.
 */

public interface requestInterface {
    @GET("rest/appVersion/version")
    Call<Test> Test(@QueryMap Map<String,Integer> map);

    @GET
    Call<Test> login(@Url String url);

    @GET("rest/appVersion/version")
    Observable<Test> retrofitTest(@QueryMap Map<String,Integer> map);

    @POST("rest/user/login")
    Observable<Response<Test>> SendLogin(@Body RequestBody requestBody);
}
