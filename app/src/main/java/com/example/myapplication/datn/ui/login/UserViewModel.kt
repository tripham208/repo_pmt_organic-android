package com.example.myapplication.datn.ui.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.datn.R
import com.example.myapplication.datn.model.entity.User
import com.example.myapplication.datn.repository.user.IUserRepository
import com.example.myapplication.datn.ui.base.BaseViewModel
import com.example.myapplication.datn.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val iUserRepository: IUserRepository) :
    BaseViewModel() {
    val user: LiveData<User> = iUserRepository.user

    private val _loginResult = MutableLiveData<Int>()
    val loginResult: LiveData<Int>
        get() = _loginResult

    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean>
        get() = _registerResult

    fun clickLogin(username: String, pass: String) {
        coroutineScope.launch {
            val invalid = checkInvalidInput(pass)
            Logger.d("$invalid")
            if (invalid) {
                _loginResult.postValue(INVALID)
            } else {
                checkLogin(username, pass)
            }
        }
    }

    private suspend fun checkLogin(username: String, pass: String) {
        val isSuccess = iUserRepository.checkLogin(username, pass)
        if (isSuccess) {
            _loginResult.postValue(SUCCESS)
        } else {
            _loginResult.postValue(FAIL)
        }
    }

    private fun checkInvalidInput(pass: String): Boolean {
        return pass.length < 6
    }

    fun register(user: User) {
        coroutineScope.launch {
            if (iUserRepository.register(user))
                _registerResult.postValue(true)
            else
                _registerResult.postValue(false)
        }

    }


    companion object {
        @StringRes
        const val INVALID = R.string.pass_invalid
        const val SUCCESS = R.string.success
        const val FAIL = R.string.fail
    }


}