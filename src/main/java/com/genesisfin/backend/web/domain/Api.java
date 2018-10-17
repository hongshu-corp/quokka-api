package com.genesisfin.backend.web.domain;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Api {
    public static <T> T createApi(Class<T> apiClass, String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(apiClass);
    }

    private static RequestBody createRequestBody(String request) {
        return RequestBody.create(MediaType.parse("application/json"), request);
    }

    public static ResponseEntity sendRequestWithIdAndParams(BiFunction<String, RequestBody, Call<ResponseBody>> request, String id, String params) {
        return sendRequest(request.apply(id, createRequestBody(params)));
    }

    public static ResponseEntity sendRequestWithId(Function<String, Call<ResponseBody>> request, String id) {
        return sendRequest(request.apply(id));
    }

    private static ResponseEntity sendRequest(Call<ResponseBody> request) {
        try {
            Response<ResponseBody> response = request.execute();
            if (response.isSuccessful()) {
                return responseEntity(response, response.body());
            } else {
                return responseEntity(response, response.errorBody());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ResponseEntity sendRequestWithParams(Function<RequestBody, Call<ResponseBody>> request, String params) {
        return sendRequest(request.apply(createRequestBody(params)));
    }

    private static ResponseEntity responseEntity(Response<ResponseBody> response, ResponseBody body) throws IOException {
        return new ResponseEntity(body.string(), HttpStatus.valueOf(response.code()));
    }
}
