package com.beepnbuy.seller.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.beepnbuy.seller.MyApplication
import com.beepnbuy.seller.data.DashboardRequestModel
import com.beepnbuy.seller.data.DashboardResponseModel
import com.beepnbuy.seller.data.DataState
import com.beepnbuy.seller.repository.AppPreference
import com.beepnbuy.seller.repository.AppRepository
import com.emxcel.beepnbuy.data.remote.BeepDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 2:48 pm.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseVM(), LifecycleObserver {


    private val _datastate : MutableLiveData<DataState<DashboardResponseModel>> = MutableLiveData()
    val dataState : LiveData<DataState<DashboardResponseModel>>
             get() = _datastate

    init {

    }

    private fun test(){

        Log.d("TAG",""+appPreference.appPrefsMethod())


        val request = DashboardRequestModel(userid = null,latitude = "23.083169598564044",longitude = "72.5248845666647",cartToken = "32d8ff5b4a52d62b")

        viewModelScope.launch {
            appRepository.getDataFromApi(request).collect {
                _datastate.value = DataState.Loading
                _datastate.value = DataState.Success(it)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onHomeActivityResume(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onHomeActivityStart(){
        test()
    }
}