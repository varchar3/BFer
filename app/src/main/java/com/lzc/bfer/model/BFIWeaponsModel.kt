package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/10/15
- @desc:
 */
data class BFIWeaponsModel(
    var avatar: String,
    var id: String,
    var userName: String,
    var weapons: MutableList<BFIWeapon>
)

data class BFIWeapon(
    var accuracy: String,
    var headshotKills: String,
    var headshots: String,
    var hitVKills: String,
    var image: String,
    var kills: String,
    var killsPerMinute: String,
    var shotsFired: Int,
    var shotsHit: Int,
    var timeEquipped: String,
    var type: String,
    var weaponName: String
)