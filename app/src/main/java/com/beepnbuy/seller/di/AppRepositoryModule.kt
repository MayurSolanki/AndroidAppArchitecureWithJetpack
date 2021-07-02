package com.beepnbuy.seller.di

import com.beepnbuy.seller.api.AppRestApi
import com.beepnbuy.seller.database.AppDatabase
import com.beepnbuy.seller.repository.AppPreference
import com.beepnbuy.seller.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mayur Solanki on 29/06/21, 3:51 pm.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppRepositoryModule {

    @Singleton
    @Provides
    
    fun provideAppRepository(
         appRestApi: AppRestApi,
         appDatabase: AppDatabase,
    ) : AppRepository{
        return AppRepository(appRestApi,appDatabase)
    }
}