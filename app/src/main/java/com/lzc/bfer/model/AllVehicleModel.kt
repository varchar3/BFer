package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/11/4
- @desc:
 */
data class AllVehicleModel(
    var avatar: String,
    var id: String,
    var userName: String,
    var vehicles: MutableList<Vehicle>
)

data class Vehicle(
    var altImage: String? = "",
    var destroyed: String,
    var image: String,
    var kills: String,
    var killsPerMinute: String,
    var timeIn: Int,
    var type: String,
    var vehicleName: String
)