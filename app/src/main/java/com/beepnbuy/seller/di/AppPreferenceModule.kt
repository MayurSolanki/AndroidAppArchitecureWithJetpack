package com.beepnbuy.seller.di

import android.content.Context
import com.beepnbuy.seller.repository.AppPreference
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Mayur Solanki on 29/06/21, 7:55 pm.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppPreferenceModule {
   //
    @Provides
    @Singleton
    fun provideAppPreference(@ApplicationContext context: Context): AppPreference{
        return   AppPreference(context)
    }
}