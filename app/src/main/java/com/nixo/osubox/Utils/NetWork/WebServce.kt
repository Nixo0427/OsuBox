package com.nixo.osubox.Utils.NetWork

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nixo.osubox.Common.APP
import com.nixo.osubox.Common.AppContext
import com.nixo.osubox.Utils.Ext.ensureDir
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


private val cacheFile by lazy{
//    File(APP.mContext!!.cacheDir,"webServiceApi").apply { ensureDir() }
}

object RetrofitClient {
    val BASE_URL =  "https://osu.ppy.sh/"
    val ATTR_URL =  "https://syrin.me/pp+/"
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))// Log级别,点进源码查看
//                    .cache(Cache(cacheFile,1024*1024*1024)) // 设置请求可用缓存1G
                    .build()
            )
            .build()
    }
    val retrofitPPP by lazy {
        Retrofit.Builder()
            .baseUrl(ATTR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .cache(Cache(cacheFile,1024*1024*1024)) // 设置请求可用缓存1G
                    .build()
            )
            .build()
    }

}