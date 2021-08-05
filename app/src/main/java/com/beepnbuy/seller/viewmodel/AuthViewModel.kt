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
class AuthViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseVM(), LifecycleObserver {

    init {

    }




}