package com.lzc.bfer.adapter.classic

import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lzc.bfer.R
import com.lzc.bfer.model.BFVc

/**
- @author:  LZC
- @time:  2021/10/14
- @desc:
 */
class BFVClassicAdapter(cData: MutableList<BFVc>) : BaseQuickAdapter<BFVc, BaseViewHolder>
    (R.layout.classic_item, cData) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val lastIndex = cData.size - 1
    @RequiresApi(Build.VERSION_CODES.M)
    override fun convert(helper: BaseViewHolder, item: BFVc) {
        val kpm = item.kpm.split(".")[0].toInt()
        Glide.with(context).load(item.altImage).into(
            helper.itemView.findViewById(R.id.iv_icons))

        helper.apply {
            setText(R.id.tv_score, "得分：${item.score}")
            setText(R.id.tv_kills, "击杀：${item.kills}")
            setText(R.id.tv_kpm, "KPM：${item.kpm}")
            setText(R.id.tv_time, "时长：${item.secondsPlayed/3600}小时")
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
            when (adapterPosition) {
                0 -> helper.setBackgroundResource(R.id.cl_classic, R.drawable.drawable_top_radius)
                lastIndex -> {
                    helper.setBackgroundResource(R.id.cl_classic, R.drawable.drawable_bottom_radius)
                }
            }
        }
    }

//    private fun setType(s:String):String = when(s) {
//        "Support" -> "支援兵"
//        "Scout" -> "侦察兵"
//        "Recon" -> "侦察兵"
//        "Medic" -> "医疗兵"
//        "Assault" -> "突击兵"
//        "Pilot" -> "空载"
//        "Tanker" -> "陆载"
//        "Cavalry" -> "骑兵"
//        else -> "兵兵"
//    }
}