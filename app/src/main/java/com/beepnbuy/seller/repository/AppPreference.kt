package com.beepnbuy.seller.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.beepnbuy.seller.util.ObserverEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 01/07/21, 10:41 am.
 */
class AppPreference @Inject constructor(@ApplicationContext context: Context) {

    private val applicationContext = context.applicationContext

    //  private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(name = "app_preferences")

    val errorData = MutableLiveData<ObserverEvent<String>>()

    fun setError(errorMsg: String) {
        errorData.postValue(ObserverEvent(errorMsg))
    }

    val successData = MutableLiveData<ObserverEvent<String>>()

    protected fun setSuccess(message: String) {
        successData.postValue(ObserverEvent(message))
    }

    fun appPrefsMethod() : String{
        return "appPrefsMethodss"
    }
}