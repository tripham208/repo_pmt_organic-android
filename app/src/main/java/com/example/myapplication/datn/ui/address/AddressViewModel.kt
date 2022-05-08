package com.example.myapplication.datn.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.datn.model.entity.address.District
import com.example.myapplication.datn.model.entity.address.Ward
import com.example.myapplication.datn.repository.address.IAddressRepository
import com.example.myapplication.datn.repository.product.IProductRepository
import com.example.myapplication.datn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val iAddressRepository: IAddressRepository
) :
    BaseViewModel() {
    val district: LiveData<List<District>> = iAddressRepository.district

    private val _wards = MutableLiveData<List<Ward>>()
    val wards: LiveData<List<Ward>>
        get() = _wards

    fun getData(){
        coroutineScope.launch {
            iAddressRepository.getData()
        }
    }

    fun getWards(int: Int){
        coroutineScope.launch {
            _wards.postValue( iAddressRepository.getWards(int))
        }
    }

}