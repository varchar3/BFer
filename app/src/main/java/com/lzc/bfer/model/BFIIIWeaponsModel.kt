package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/10/15
- @desc:
 */
data class BFIIIWeaponsModel(
    var avatar: String,
    var id: Long,
    var userName: String,
    var weapons: MutableList<BFIIIWeapon>
)

data class BFIIIWeapon(
    var accuracy: String,
    var altImage: String,
    var headshots: String,
    var image: String,
    var kills: String,
    var killsPerMinute: String,
    var type: String,
    var weaponName: String
)