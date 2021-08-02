package com.beepnbuy.seller.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.beepnbuy.seller.data.*
import com.beepnbuy.seller.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 25/06/21, 2:48 pm.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseVM(), LifecycleObserver {

    private val _datastate : MutableLiveData<DataState<List<HomeProductItemDataModel>>> = MutableLiveData()
    val dataState : LiveData<DataState<List<HomeProductItemDataModel>>> = _datastate

//   private var _benchMark = MutableStateFlow<DataState<DataState.Loading>>(DataState.Loading).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DataState.Loading)
//      var material : StateFlow<DataState<DataState.Loading>> = _benchMark

    init {

    }

    private fun testLiveData(){

        Log.d("TAG",""+appPreference.appPrefsMethod())

        val request = DashboardRequestModel(userid = null,latitude = "23.083169598564044",longitude = "72.5248845666647" ,cartToken = "32d8ff5b4a52d62b")

       // val request = DashboardRequestModel(userid = null,latitude = null,longitude = null ,cartToken = null)

        // observe some changes on io and then print result on main
        // dispatcher always will be same as in the scope in which we’re collecting data from our flow.
        //By default, the producer of a flow builder executes in the CoroutineContext of the coroutine that collects from it, and
        viewModelScope.launch {


            appRepository.getDataFromApi(request)
                .flowOn(Dispatchers.IO)
                .collect {
                    when(it){
                            is DataState.Error ->  _datastate.value = DataState.Error(it.exception, it.errorCode)
                            is DataState.Loading ->   _datastate.value = DataState.Loading
                            is DataState.Success -> {
                                val someValue : List<HomeProductItemDataModel> =  it.data!!.homeProductItemDataModelList.take(1)
                                _datastate.value = DataState.Success(someValue)
                            }

                    }
            }
        }
    }

  /*  private fun testSharedAndStateFlow(){

        Log.d("TAG",""+appPreference.appPrefsMethod())

        val request = DashboardRequestModel(userid = null,latitude = "23.083169598564044",longitude = "72.5248845666647" ,cartToken = "32d8ff5b4a52d62b")

        // observe some changes on io and then print result on main
        // dispatcher always will be same as in the scope in which we’re collecting data from our flow.
        //By default, the producer of a flow builder executes in the CoroutineContext of the coroutine that collects from it, and
        viewModelScope.launch {
            appRepository.getDataFromApi(request)
                .flowOn(Dispatchers.IO)
                .collect {
                    when(it){
                        is DataState.Error ->  _benchMark.value = ""

                        is DataState.Loading ->   _benchMark.value = DataStatus()
                        is DataState.Success -> {
                            val someValue : List<HomeProductItemDataModel> =  it.data!!.homeProductItemDataModelList.take(1)
                            _benchMark.value =
                        }
                        is DataState.InitialState ->{
                            _benchMark.value = DataState.Loading
                        }
                    }
                }
        }
    }*/

   /* fun testMthd1() : LiveData<DataState<List<HomeProductItemDataModel>>> = liveData {
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

    }*/






    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onHomeActivityResume(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onHomeActivityStart(){
       // testMthd1()
        testLiveData()
        //testSharedAndStateFlow()
    }

   /* fun getPupImageFlow(): Flow<PupImageState>{
        return queryStateFlow
            .debounce(300)
            .filter {it.isNotEmpty()}
            .flatMapLatest {query ->
                flow {
                    emit(getPupImage(query))
                }.onStart {
                    emit(PupImageState.InProgress)
                }.catch {
                    emit(PupImageState.Error(Exception("Sorry, there are no pups by that name. Keep looking!")))
                }
            }
            .flowOn(Dispatchers.IO)
    }*/
}