package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/9/29
- @desc:
 */

data class BFVWeaponsModel(
    var cache: Boolean,
    var weapons: MutableList<BFVWeapon>
)

data class BFVWeapon(
    var accuracy: String,
    var headshots: String,
    var hitVKills: Double,
    var image: String,
    var kills: String,
    var killsPerMinute: Double,
    var type: String,
    var weaponName: String
)

