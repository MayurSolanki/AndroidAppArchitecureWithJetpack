package com.beepnbuy.seller.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beepnbuy.seller.repository.AppPreference
import com.beepnbuy.seller.util.ObserverEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Mayur Solanki on 29/06/21, 3:00 pm.
 */
open class BaseVM : ViewModel() {


    @Inject
    lateinit var appPreference : AppPreference

//    //create a new Job
//    open val parentJob: Job = SupervisorJob()
//
//    //create a coroutine context with the job and the dispatcher
//    val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.IO
//
//    //create a coroutine scope with the coroutine context
//    open val scope = CoroutineScope(coroutineContext)



    override fun onCleared() {
//        parentJob.cancel()
        super.onCleared()
    }

}