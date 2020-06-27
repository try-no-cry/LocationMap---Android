package com.example.locationmap.Retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IRetrofit
{


    @Headers({"Accept:application/json"})
    @POST("/api/currentLocation")
    Call<JsonObject> get_location(@Body JsonObject object);
}
