package com.beepnbuy.seller.di

import com.beepnbuy.seller.AppConstant
import com.beepnbuy.seller.api.ApiClient
import com.beepnbuy.seller.api.AppRestApi
import com.beepnbuy.seller.repository.AppRemoteRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Mayur Solanki on 28/06/21, 2:43 pm.
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
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