//package com.genesisfin.backend.web.domain;
//
//import okhttp3.RequestBody;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.http.Body;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.Path;
//
//public interface PaymentApi {
//
//    @POST("/members/{id}/accounts")
//    Call<ResponseBody> createPaymentAccount(@Path("id") String id, @Body RequestBody request);
//
//    @POST("/members/{id}/transactions")
//    Call<ResponseBody> chargeMember(@Path("id") String id, @Body RequestBody params);
//
//    @GET("/members/{id}/accounts")
//    Call<ResponseBody> getPaymentAccounts(@Path("id") String id);
//
//}
