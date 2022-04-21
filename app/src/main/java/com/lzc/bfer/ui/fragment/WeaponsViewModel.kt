package com.lzc.bfer.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lzc.bfer.base.BaseViewModel
import com.lzc.bfer.ext.getOrNullOnSafe
import com.lzc.bfer.model.*
import com.lzc.bfer.net.RetrofitHelper
import kotlinx.coroutines.launch

class WeaponsViewModel : BaseViewModel() {

    val TAG = "MainViewModel"
    val bf5 = MutableLiveData<List<BFVWeapon>>()
    val bf1 = MutableLiveData<List<BFIWeapon>>()
    val bf4 = MutableLiveData<List<BFIVWeapon>>()
    val bf3 = MutableLiveData<List<BFIIIWeapon>>()

    val bfVWeapons = MutableLiveData<BFVWeaponsModel>()
    val bfIWeapons = MutableLiveData<BFIWeaponsModel>()
    val bfIVWeapons = MutableLiveData<BFIVWeaponsModel>()
    val bfIIIWeapons = MutableLiveData<BFIIIWeaponsModel>()

    fun getIWeaponMessage(name: String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIWeapons(name) }
            if (result.isSuccess){
                bfIWeapons.postValue(result.getOrNullOnSafe())
            }else{
                Log.i(TAG, "fool")
            }
        }
    }

    fun getVWeaponMessage(name: String,platform: String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getVWeapons(name,platform) }
            if (result.isSuccess){
                bfVWeapons.postValue(result.getOrNullOnSafe())
            }else{
                Log.i(TAG, "fool")
            }
        }
    }

    fun getIVWeaponMessage(name: String,platform: String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIVWeapons(name,platform) }
            if (result.isSuccess){
                bfIVWeapons.postValue(result.getOrNullOnSafe())
            }else{
                Log.i(TAG, "fool")
            }
        }
    }

    fun getIIIWeaponMessage(name: String,platform: String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIIIWeapons(name,platform) }
            if (result.isSuccess){
                bfIIIWeapons.postValue(result.getOrNullOnSafe())
            }else{
                Log.i(TAG, "fool")
            }
        }
    }
}