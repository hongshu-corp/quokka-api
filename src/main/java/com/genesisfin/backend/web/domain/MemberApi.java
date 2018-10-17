package com.genesisfin.backend.web.domain;

import com.genesisfin.backend.web.security.Credential;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MemberApi {

    @POST("/credential")
    Call<Credential> getCredential(@Body Credential request);

    @POST("/members")
    Call<ResponseBody> register(@Body RequestBody requestBody);

    @POST("/sendcode")
    Call<ResponseBody> sendCode(@Body RequestBody requestBody);
}
