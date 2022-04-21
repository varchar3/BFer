package com.lzc.bfer.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lzc.bfer.base.BaseViewModel
import com.lzc.bfer.ext.getOrNullOnSafe
import com.lzc.bfer.model.AllVehicleModel
import com.lzc.bfer.model.Vehicle
import com.lzc.bfer.net.ApiCodeException
import com.lzc.bfer.net.RetrofitHelper
import kotlinx.coroutines.launch

class VehicleViewModel : BaseViewModel() {
    val v = MutableLiveData<AllVehicleModel>()
    val sv = MutableLiveData<MutableList<Vehicle>>()
    val error = MutableLiveData<Unit>()

    fun getVehicle(name: String,platform: String?,game: Int){
        viewModelScope.launch {
            val result = when(game){
                0-> apiCall { RetrofitHelper.api.getVVehicles(name,platform!!) }
                1-> apiCall { RetrofitHelper.api.getIVVehicles(name,platform!!) }
                2-> apiCall { RetrofitHelper.api.getIVehicles(name) }
                3-> apiCall { RetrofitHelper.api.getIIIVehicles(name,platform!!) }
                else -> Result.failure(ApiCodeException(403,"page_error"))
            }
            if (result.isSuccess){
                v.postValue(result.getOrNullOnSafe())
            }else{
                error.postValue(Unit)
            }
        }

    }
}