package com.lzc.bfer.local

import com.lzc.bfer.model.BFVStatesModel
import com.lzc.bfer.net.RetrofitHelper
import com.tencent.mmkv.MMKV

/**
- @author:  LZC
- @time:  2021/9/1
- @desc:
 */
interface SignIn {
    fun signIn(user: BFVStatesModel)
    fun isSnapSigned(): Boolean
}

class SignHandler : SignIn {

    var user: BFVStatesModel? = null
        set(value) {
            field = value
            val v = if (value == null) null else {
                RetrofitHelper.gson.toJson(value)
            }
            MMKV.defaultMMKV().encode(PREF_USER, v)
        }

    override fun signIn(user: BFVStatesModel) {
    }

    override fun isSnapSigned(): Boolean {
        return true

    }

    companion object {//lazyType
        const val PREF_USER = "pref_user"

        @Volatile// volatile 保证可见性和禁止指令重排序,多进程不安全
        private var INSTANCE: SignHandler? = null

        /**
         * through volatile to check if it is null? ->
         * <yes> -> create new
         * <no> -> fetch from thread
         * */

        fun getInstance() =
            INSTANCE ?: kotlin.synchronized(SignHandler::class.java) {//
                INSTANCE ?: SignHandler()
                    .also { INSTANCE = it }
            }
    }


}