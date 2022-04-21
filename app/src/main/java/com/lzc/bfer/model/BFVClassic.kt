package com.lzc.bfer.model

/**
- @author:  LZC
- @time:  2021/11/5
- @desc:
 */
data class BFClassic(
    var avatar: String,
    var classes: MutableList<BFVc>,
    var id: String,
    var userName: String,
)

data class BFVc(
    var altImage: String,
    var className: String,
    var image: String,
    var kills: Int,
    var kpm: String,
    var score: Int,
    var secondsPlayed: Int,
    var timePlayed: String,
)