package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/9/1
- @desc:
 */
data class ResultSet<T>(
    var code: Int = 200,
    var msg: String?,
    var data: T
)

