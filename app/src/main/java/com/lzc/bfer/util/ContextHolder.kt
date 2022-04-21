package com.lzc.bfer.util

import android.annotation.SuppressLint
import android.content.Context

//全局获取context，初始化时加入
class ContextHolder {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}