package com.lzc.bfer.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lzc.bfer.base.BaseViewModel
import com.lzc.bfer.ext.getOrNullOnSafe
import com.lzc.bfer.model.*
import com.lzc.bfer.net.RetrofitHelper
import kotlinx.coroutines.launch
import retrofit2.http.Query

class HomeViewModel : BaseViewModel() {
    val TAG = "MainViewModel"
    val c = MutableLiveData<BFClassic>()
    val bfIStatus= MutableLiveData<BFIStatesModel>()//战地1跟5接的一样
    val bfVStatus= MutableLiveData<BFVStatesModel>()//战地1跟5接的一样
    val bfIVStatus= MutableLiveData<BFIVStatesModel>()
    val bfIIIStatus= MutableLiveData<BFIIIStatesModel>()
    val noSuchPeople =  MutableLiveData<String>()

    fun fetchBFIStatus(name:String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIStatus(name) }
            if (result.isSuccess){
                bfIStatus.postValue(result.getOrNullOnSafe())
            }
            if (result.isFailure){
                noSuchPeople.postValue("这个一战兵好像没在战场")
            }
        }
    }

    fun fetchBFVStatus(name:String,platform:String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getVStatus(name,platform) }
            if (result.isSuccess){
                bfVStatus.postValue(result.getOrNullOnSafe())
            }
            if (result.isFailure){
                noSuchPeople.postValue("这个二战兵好像没在战场")
            }
        }
    }

    fun fetchBFVClass(name:String,platform:String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getVClasses(name,platform) }
            if (result.isSuccess){
                c.postValue(result.getOrNullOnSafe())
            }
            if (result.isFailure){
                noSuchPeople.postValue("(⊙﹏⊙)好像错误惹~")
            }
        }
    }

    fun fetchBFIClass(name:String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIClasses(name) }
            if (result.isSuccess){
                c.postValue(result.getOrNullOnSafe())
            }
            if (result.isFailure){
                noSuchPeople.postValue("(⊙﹏⊙)好像错误惹~")
            }
        }
    }

    fun fetchBFIVStatus(name:String,platform:String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIVStatus(name,platform) }
            if (result.isSuccess){
                bfIVStatus.postValue(result.getOrNullOnSafe())
            }
            if (result.isFailure){
                noSuchPeople.postValue("这个现代战士好像没在战场")
            }
        }
    }

    fun fetchBFIIIStatus(name:String,platform:String){
        viewModelScope.launch {
            val result = apiCall { RetrofitHelper.api.getIIIStatus(name,platform) }
            if (result.isSuccess){
                bfIIIStatus.postValue(result.getOrNullOnSafe())
            }
            if (result.isFailure){
                noSuchPeople.postValue("这个现代战士好像没在战场")
            }
        }
    }
}