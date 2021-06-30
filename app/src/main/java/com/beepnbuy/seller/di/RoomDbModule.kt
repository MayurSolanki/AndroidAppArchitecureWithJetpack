package com.beepnbuy.seller.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.beepnbuy.seller.database.AppDao
import com.beepnbuy.seller.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mayur Solanki on 28/06/21, 2:38 pm.
 */

@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context,
             AppDatabase::class.java,"bnb_seller_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase) : AppDao{
       return appDatabase.appDao()
    }
}