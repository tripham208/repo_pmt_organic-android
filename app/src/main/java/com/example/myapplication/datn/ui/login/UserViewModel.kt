package com.example.myapplication.datn.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.datn.MarsApi
import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.ui.base.BaseViewModel
import com.example.myapplication.datn.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(): BaseViewModel() {
/*

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
     fun getMarsPhotos() {

        viewModelScope.launch {
            Logger.d("here")
            val listResult = MarsApi.retrofitService.getPhotos()
            Logger.d("$listResult")
        }
    }*/
}