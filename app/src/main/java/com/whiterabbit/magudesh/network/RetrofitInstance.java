package com.whiterabbit.magudesh.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Magudesh on 9/12/2020.
 */

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://www.mocky.io/v2/";

    private RetrofitInstance() {}

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
