package com.lzc.bfer.net

import com.lzc.bfer.model.*
import okhttp3.ResponseBody
import retrofit2.http.*

/**
- @author:  LZC
- @time:  2021/9/1
- @desc: 创建网络请求的 retrofit2 接口函数
 */
interface Apis {
    /**
     * ------------------------------V-----------------------------------
     * */
    @GET("bfv/stats/?lang=en-us")
    suspend fun getVStatus(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFVStatesModel

    @GET("bfv/weapons/?lang=en-us")
    suspend fun getVWeapons(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFVWeaponsModel

    @GET("bfv/vehicles/?lang=en-us")
    suspend fun getVVehicles(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): AllVehicleModel

    @GET("bfv/classes/?lang=en-us")
    suspend fun getVClasses(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFClassic
    /**
     * ------------------------------I----------------------------------
     * */
    @GET("bf1/stats/?lang=en-us&format_values=true")
    suspend fun getIStatus(
        @Query("name") name:String
    ): BFIStatesModel

    @GET("bf1/weapons/?lang=en-us&format_values=true")
    suspend fun getIWeapons(
        @Query("name") name:String
    ): BFIWeaponsModel

    @GET("bf1/vehicles/?lang=en-us&format_values=true")
    suspend fun getIVehicles(
        @Query("name") name:String
    ): AllVehicleModel

    @GET("bf1/classes/?lang=en-us&format_values=true")
    suspend fun getIClasses(
        @Query("name") name:String
    ): BFClassic

    /**
     * ------------------------------IV---------------------------------
     * */
    @GET("bf4/stats/?")
    suspend fun getIVStatus(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFIVStatesModel

    @GET("bf4/weapons/?")
    suspend fun getIVWeapons(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFIVWeaponsModel

    @GET("bf4/vehicles/?")
    suspend fun getIVVehicles(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): AllVehicleModel

    /**
     * ------------------------------III---------------------------------
     * */
    @GET("bf3/stats/")
    suspend fun getIIIStatus(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFIIIStatesModel

    @GET("bf3/weapons/")
    suspend fun getIIIWeapons(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): BFIIIWeaponsModel

    @GET("bf3/vehicles/")
    suspend fun getIIIVehicles(
        @Query("name") name:String,
        @Query("platform") platform:String
    ): AllVehicleModel
}