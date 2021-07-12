package com.beepnbuy.seller.repository

import android.util.Log
import com.beepnbuy.seller.api.AppRestApi
import com.beepnbuy.seller.convertToMap
import com.beepnbuy.seller.data.DashboardRequestModel
import com.beepnbuy.seller.data.DashboardResponseModel
import com.beepnbuy.seller.data.DataState
import com.beepnbuy.seller.database.AppDatabase
import com.beepnbuy.seller.getType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    suspend fun getDataFromApi(requestModel: DashboardRequestModel) : Flow<DataState<DashboardResponseModel>>  = flow{

            val data = appRestApi.getRequest<DashboardResponseModel>(
                "search/dashboard",
                requestModel.convertToMap(),
                getType<DashboardResponseModel>()
            ){ errorMsg, errorCode ->
                emit(DataState.Error(errorMsg,errorCode))
                Log.d("TAG", "$errorMsg")
            }

            if(data != null){
                emit(DataState.Success(data))
            }

       }

    suspend fun getDataFromLocalDb() {
        appDatabase.appDao().deleteAll()
    }



}