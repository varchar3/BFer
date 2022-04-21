package com.lzc.bfer.net

import android.util.Log
import com.lzc.bfer.ext.toast
import com.lzc.bfer.util.ContextHolder
import com.lzc.bfer.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

/**
- @author:  LZC
- @time:  2021/6/2
- @desc:
 */

@FunctionalInterface
interface NetworkCall {

    suspend fun <T : Any> apiCall(call: suspend () -> T): Result<T?> {
        val response = try {
                call.invoke()
            } catch (t: Throwable) {
                t
            }

        if (response is Exception) {
            Log.e("fetch", response.toString())
            return Result.failure(response)
        }

        response as T
//        if (response.code != 200) {
//            Log.e("NetworkCall", "${response.code} ${response.msg}")
//            if (response.code == 401) {
//                withContext(Dispatchers.Main) {
//                    ContextHolder.context.toast(
//                        response.msg ?: ContextHolder.context.getString(R.string.please_login))
//                }
//            }
//            return Result.failure(ApiCodeException(response.code,response.msg?:""))
//        }
        @Suppress("UNCHECKED_CAST")
        return Result.success(response as T?)
    }

    suspend fun request(job: suspend () -> ResponseBody) {
        if (!ConnectCheck.isConnected()) {
            val msg = ContextHolder.context.getString(R.string.please_check_network)
            withContext(Dispatchers.Main) {
                ContextHolder.context.toast(msg)
            }
            return
        }

        try {
            job.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}