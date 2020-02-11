package com.moringaschool.garbage.services;


import com.moringaschool.garbage.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = Constants.MOVIE_DB_BASE_URL;

    public static Retrofit getClient(){
       if (retrofit == null){
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
    }
}
