package com.beepnbuy.seller.repository

import android.util.Log
import com.beepnbuy.seller.api.AppRestApi
import com.beepnbuy.seller.convertToMap
import com.beepnbuy.seller.data.DashboardRequestModel
import com.beepnbuy.seller.data.DashboardResponseModel
import com.beepnbuy.seller.database.AppDatabase
import com.beepnbuy.seller.getType
import com.emxcel.beepnbuy.data.remote.BeepDataResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Mayur Solanki on 28/06/21, 3:09 pm.
 */

//    @Inject  lateinit var appRestApi: AppRestApi
//    @Inject  lateinit var appDatabase: AppDatabase

//@Inject constructor
class AppRepository (
    private val  appRestApi: AppRestApi,
    private val appDatabase: AppDatabase,
) {


    @Inject
    lateinit var appPreference : AppPreference


    suspend fun getDataFromApi(requestModel: DashboardRequestModel) : Flow<DashboardResponseModel> {
       return flow<DashboardResponseModel> {
           val data = appRestApi.getRequest<DashboardResponseModel>(
               "search/dashboard",
               requestModel.convertToMap(),
               getType<DashboardResponseModel>()
           ) { errorMsg ->
               Log.d("TAG", "$errorMsg")
              // appPreference.setError(errorMsg)
           }

           data?.let {
               emit(data)
           }
       }
    }

    suspend fun getDataFromLocalDb() {
        appDatabase.appDao().deleteAll()
    }



}