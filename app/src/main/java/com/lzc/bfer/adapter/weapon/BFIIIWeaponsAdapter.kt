package com.lzc.bfer.adapter.weapon

import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lzc.bfer.R
import com.lzc.bfer.model.BFIIIWeapon

/**
- @author:  LZC
- @time:  2021/10/14
- @desc:
 */
class BFIIIWeaponsAdapter(weaponsData: MutableList<BFIIIWeapon>) : BaseQuickAdapter<BFIIIWeapon, BaseViewHolder>
    (R.layout.weapon_item, weaponsData) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun convert(helper: BaseViewHolder, item: BFIIIWeapon) {
        val acc = item.accuracy.split(".")[0].toInt()
        val heads= item.headshots.split(".")[0].toInt()
        Glide.with(context).load(item.image).into(
            helper.itemView.findViewById(R.id.iv_weapons))

        helper.apply {
            setBackgroundColor(R.id.iv_weapons,context.getColor(R.color.transparent1))
            setText(R.id.weapon_name, item.weaponName)
            setText(R.id.tv_kills_num, item.kills)
            setText(R.id.tv_kpm, item.killsPerMinute)
            setText(R.id.tv_accuracy, item.accuracy)
            when {
                acc >=70 -> {
                    setTextColor(R.id.tv_accuracy,context.resources.getColor(R.color.serious))
                }
                acc >=50 -> {
                    setTextColor(R.id.tv_accuracy,context.resources.getColor(R.color.second_warn))
                }
                acc >= 30 -> {
                    setTextColor(R.id.tv_accuracy,context.resources.getColor(R.color.warn))
                }
            }
            setText(R.id.tv_head_shot, item.headshots)
            when {
                heads >=70 -> {
                    setTextColor(R.id.tv_head_shot,context.resources.getColor(R.color.serious))
                }
                heads >=50 -> {
                    setTextColor(R.id.tv_head_shot,context.resources.getColor(R.color.second_warn))
                }
                heads >= 30 -> {
                    setTextColor(R.id.tv_head_shot,context.resources.getColor(R.color.warn))
                }
            }
        }
    }
}