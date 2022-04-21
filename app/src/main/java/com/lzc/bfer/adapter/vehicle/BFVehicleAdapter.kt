package com.lzc.bfer.adapter.vehicle

import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lzc.bfer.R
import com.lzc.bfer.model.BFIIIWeapon
import com.lzc.bfer.model.Vehicle

/**
- @author:  LZC
- @time:  2021/10/14
- @desc:
 */
class BFVehicleAdapter(vehiclesData: MutableList<Vehicle>) : BaseQuickAdapter<Vehicle, BaseViewHolder>
    (R.layout.vehicle_item, vehiclesData) {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun convert(helper: BaseViewHolder, item: Vehicle) {
        val kpm = item.killsPerMinute.split(".")[0].toInt()
        Glide.with(context).load(item.image).into(
            helper.itemView.findViewById(R.id.iv_vehicle))

        helper.apply {
            setText(R.id.vehicle_name, item.vehicleName)
            setText(R.id.tv_v_kills, item.kills)
            setText(R.id.tv_v_kpm, item.killsPerMinute)
            when {
                kpm >=7 -> {
                    setTextColor(R.id.tv_v_kpm,context.resources.getColor(R.color.serious))
                }
                kpm >=4 -> {
                    setTextColor(R.id.tv_v_kpm,context.resources.getColor(R.color.second_warn))
                }
                kpm >= 2 -> {
                    setTextColor(R.id.tv_v_kpm,context.resources.getColor(R.color.warn))
                }
            }
            setText(R.id.tv_time_in, item.timeIn.toString())
            setText(R.id.tv_destroyed, item.destroyed)

        }
    }
}