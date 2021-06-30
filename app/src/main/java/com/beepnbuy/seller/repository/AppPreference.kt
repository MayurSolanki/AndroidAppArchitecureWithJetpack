package com.beepnbuy.seller.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import com.beepnbuy.seller.util.ObserverEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import java.util.prefs.Preferences
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 28/06/21, 10:24 pm.
 */


class AppPreference @Inject constructor(@ApplicationContext context: Context) {

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