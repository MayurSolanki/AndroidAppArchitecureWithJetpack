package com.beepnbuy.seller.di

import com.beepnbuy.seller.AppConstant
import com.beepnbuy.seller.BuildConfig
import com.beepnbuy.seller.MyApplication
import com.beepnbuy.seller.api.ApiClient
import com.beepnbuy.seller.api.AppRestApi
import com.beepnbuy.seller.repository.AppRemoteRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Mayur Solanki on 28/06/21, 2:43 pm.
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val TIME_OUT_SEC: Long = 30 // 30 seconds
    private const val cacheSize = 10 * 1024 * 1024
    private val cache : Cache = Cache(MyApplication.appCache, cacheSize.toLong())

    private val httpLoggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


    @Provides
    @Singleton
    fun provideAppRestApi(apiClient: ApiClient) : AppRestApi {
        return AppRemoteRepository(apiClient)
    }


}