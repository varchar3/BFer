package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/10/14
- @desc:
 */
data class BFIVWeaponsModel(
    var avatar: String,
    var id: String,
    var userName: String,
    var weapons: MutableList<BFIVWeapon>
)

data class BFIVWeapon(
    var accuracy: String,
    var altImage: String,
    var headshots: String,
    var image: String,
    var kills: String,
    var killsPerMinute: String,
    var type: String,
    var weaponName: String
)
