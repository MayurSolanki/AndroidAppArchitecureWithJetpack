package com.beepnbuy.seller.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.beepnbuy.seller.MyApplication
import com.beepnbuy.seller.data.DashboardRequestModel
import com.beepnbuy.seller.data.DashboardResponseModel
import com.beepnbuy.seller.data.DataState
import com.beepnbuy.seller.data.HomeProductItemDataModel
import com.beepnbuy.seller.repository.AppPreference
import com.beepnbuy.seller.repository.AppRepository
import com.emxcel.beepnbuy.data.remote.BeepDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 2:48 pm.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseVM(), LifecycleObserver {

    private val _datastate : MutableLiveData<DataState<List<HomeProductItemDataModel>>> = MutableLiveData()
    val dataState : LiveData<DataState<List<HomeProductItemDataModel>>> get() = _datastate


    init {

    }

    private fun test(){

        Log.d("TAG",""+appPreference.appPrefsMethod())

        val request = DashboardRequestModel(userid = null,latitude = "23.083169598564044",longitude = "72.5248845666647" ,cartToken = "32d8ff5b4a52d62b")

        // observe some changes on io and then print result on main
        // dispatcher always will be same as in the scope in which we’re collecting data from our flow.
        //By default, the producer of a flow builder executes in the CoroutineContext of the coroutine that collects from it, and
        viewModelScope.launch {

            appRepository.getDataFromApi(request)
                .flowOn(Dispatchers.Default)
                .collect {
                    when(it){
                            is DataState.Error ->  _datastate.value = it
                            is DataState.Loading ->   _datastate.value = DataState.Loading
                            is DataState.Success -> {
                                val someValue : List<HomeProductItemDataModel> =  it.data!!.homeProductItemDataModelList.take(1)
                                _datastate.value = DataState.Success(someValue)
                            }
                        }
            }
        }
    }

    fun testMthd1() : LiveData<DataState<List<HomeProductItemDataModel>>> = liveData {
//        Log.d("TAG",""+appPreference.appPrefsMethod())

        val request = DashboardRequestModel(userid = null,latitude = "23.083169598564044",longitude = "72.5248845666647",cartToken = "32d8ff5b4a52d62b")

            appRepository.getDataFromApi(request).collect {
                when(it){
                    is DataState.Error -> emit(it)
                    is DataState.Loading ->  emit(DataState.Loading)
                    is DataState.Success -> {
                        val someValue : List<HomeProductItemDataModel> =  it.data!!.homeProductItemDataModelList.take(1)
                        emit(DataState.Success(someValue))
                    }
                }
            }

    }






    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onHomeActivityResume(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onHomeActivityStart(){
       // testMthd1()
        test()
    }
}