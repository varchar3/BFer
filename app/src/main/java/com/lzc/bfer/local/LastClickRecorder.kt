package com.lzc.bfer.local

import com.lzc.bfer.model.*
import com.lzc.bfer.ui.fragment.VehicleViewModel

/**
- @author:  LZC
- @time:  2021/10/15
- @desc:
 */
class LastClickRecorder {
    var searchName: String? = ""
    var platform:String? = "pc"
    var bf5Weapons: BFVWeaponsModel? = null
    var bf1Weapons: BFIWeaponsModel? = null
    var bf3Weapons: BFIIIWeaponsModel? = null
    var bf4Weapons: BFIVWeaponsModel? = null

    var vehicles:AllVehicleModel?=null

    companion object {//lazyType

        @Volatile// volatile 保证可见性和禁止指令重排序,多进程不安全
        private var INSTANCE: LastClickRecorder? = null

        /**
         * through volatile to check if it is null? ->
         * <yes> -> create new
         * <no> -> fetch from thread
         * */

        fun getInstance() =
            INSTANCE ?: synchronized(LastClickRecorder::class.java) {//
                INSTANCE ?: LastClickRecorder()
                    .also { INSTANCE = it }
            }
    }
}