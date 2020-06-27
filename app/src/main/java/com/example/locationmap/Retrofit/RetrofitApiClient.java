package com.example.locationmap.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static final String BASE_URL ="http://192.168.43.77:3000" ;
    public static Retrofit retrofit;
    public static IRetrofit retrofitApiService;

    public static IRetrofit getRetrofitApiService()
    {
        if(retrofitApiService==null)
        {
            retrofitApiService=retrofit.create(IRetrofit.class);
        }
        return retrofitApiService;
    }

    public static Retrofit getClient()
    {
        if(retrofit==null)
        {
            Gson gson=new GsonBuilder().create();
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }

        return retrofit;
    }
}
