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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val iUserRepository: IUserRepository) :
    BaseViewModel() {
    val user: LiveData<User> = iUserRepository.user

    private val _loginResult = MutableLiveData<Int>()
    val loginResult: LiveData<Int>
        get() = _loginResult

    private val _registerResult = MutableLiveData<Int>()
    val registerResult: LiveData<Int>
        get() = _registerResult

    private val _time = MutableLiveData<Int>()
    val time: LiveData<Int>
        get() = _time

    fun clickLogin(username: String, pass: String) {
        coroutineScope.launch {
            val invalid = checkInvalidInput(pass)
            val invalid2 = checkInvalidInput(username)
            Logger.d("$invalid")
            if (invalid || invalid2) {
                _loginResult.postValue(INVALID)
            } else {
                checkLogin(username, pass)
            }
        }
    }

    fun loginByPhone(phone: Int) {
        coroutineScope.launch {

            if (iUserRepository.checkPhone(int = phone)) {
                _loginResult.postValue(SUCCESS)
            } else {
                _loginResult.postValue(NO_ACCOUNT)
            }
        }
    }

    fun updateUser(user: User) {
        coroutineScope.launch {
            iUserRepository.updateUser(user)
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

    fun logout() {
        _loginResult.value = LOG_OUT
        coroutineScope.launch {
            iUserRepository.logoutUser()
        }
    }

    private fun checkInvalidInput(pass: String): Boolean {
        return pass.length < 6
    }


    fun register(user: User) {
        coroutineScope.launch {
            val invalid = checkInvalidInput(user.username)
            val invalid2 = checkInvalidInput(user.password)
            val invalid3 = checkInvalidInput(user.name)
            if (invalid || invalid2 || invalid3) {
                _registerResult.postValue(INVALID)
            } else {
                if (iUserRepository.register(user)) {
                    _registerResult.postValue(SUCCESS)
                } else
                    _registerResult.postValue(EXITS)
            }

        }

    }

    fun reSendTime() {
        coroutineScope.launch {
            var time = 60000
            while (time >= 0) {
                _time.postValue(time)
                time -= 1000
                delay(1000)
            }

        }
    }


    companion object {
        @StringRes
        const val INVALID = R.string.pass_invalid
        const val SUCCESS = R.string.success
        const val FAIL = R.string.fail
        const val LOG_OUT = R.string.logout
        const val NO_ACCOUNT = R.string.space
        const val EXITS = R.string.fail
    }


}