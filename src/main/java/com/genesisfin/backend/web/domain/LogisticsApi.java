package com.genesisfin.backend.web.domain;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogisticsApi {

    @POST("/rates")
    Call<ResponseBody> rates(@Body RequestBody request);

    @POST("/labels")
    Call<ResponseBody> createLabel(@Body RequestBody request);
}
