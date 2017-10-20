package com.dokkastudios.enkore.services.util;

import com.dokkastudios.enkore.services.EnkorServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class RetrofitInstance
{
    private static final RetrofitInstance INSTANCE = new RetrofitInstance();
    private static Retrofit mRetrofit = null;
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    private RetrofitInstance() {}

    /**
     *
     * @return
     */
    static public Retrofit getRetrofit()
    {
        if(INSTANCE.mRetrofit == null)
            INSTANCE.mRetrofit = new Retrofit.Builder()
                    .baseUrl(EnkorServices.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        return INSTANCE.mRetrofit;
    }
}