package com.onemorebit.pimtha.http

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Euro on 2/19/16 AD.
 */

object RetroAdapter{
    val BASE_URL = "http://ripzery.com/api/"

      fun createApiService() : RipzeryApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        return retrofit.create(RipzeryApiService::class.java)
    }
}