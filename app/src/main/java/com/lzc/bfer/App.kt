package com.lzc.bfer

import android.app.Application
import com.lzc.bfer.util.ContextHolder
import com.tencent.mmkv.MMKV

/**
- @author:  LZC
- @time:  2021/9/1
- @desc:
 */
class App : Application() {
    companion object{
        lateinit var instance: Application
    }
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        ContextHolder.context = applicationContext
    }
}