package com.lzc.bfer.ui

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayout
import com.lzc.bfer.BuildConfig
import com.lzc.bfer.R
import com.lzc.bfer.base.BaseActivity
import com.lzc.bfer.databinding.ActivityEntryBinding
import com.lzc.bfer.databinding.CustomerViewBinding
import com.lzc.bfer.ext.toast
import com.lzc.bfer.local.LastClickRecorder
import com.tencent.mmkv.MMKV

class EntryActivity : BaseActivity() {

    private var _binding: ActivityEntryBinding? = null
    private val binding get() = _binding!!
    private val kv = MMKV.defaultMMKV()
    private val gameTab = arrayOf(
        "战地5",
        "战地4",
        "战地1",
        "战地3"
    )

    private val platformTab = arrayOf(
        "PC",
        "PS3",
        "PS4",
        "XBOX 360",
        "XBOX One"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextStep.setOnClickListener { go() }
        if (MMKV.defaultMMKV().decodeString("preName")!= ""){
            binding.useOld.text = "使用已有查询-->${MMKV.defaultMMKV().decodeString("preName")}"
            binding.useOld.visibility = View.VISIBLE
        }
        binding.useOld.setOnClickListener { MainActivity.start(this@EntryActivity) }

        for (i in gameTab.indices){
            val newTab = binding.tabLayout.newTab()
            val cv = CustomerViewBinding.inflate(layoutInflater)
            cv.tvTitle.text = gameTab[i]
            newTab.customView = cv.root
            binding.tabLayout.addTab(newTab)
            if (i != 0){
                cv.clCv.background = null
            }
        }

        for (i in platformTab.indices){
            val newTab = binding.tabLayout2.newTab()
            val cv = CustomerViewBinding.inflate(layoutInflater)
            cv.tvTitle.text = platformTab[i]
            newTab.customView = cv.root
            binding.tabLayout2.addTab(newTab)
            if (i != 0){
                cv.clCv.background = null
            }
        }

        LastClickRecorder.getInstance().platform ="pc"

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<ConstraintLayout>(R.id.cl_cv)
                    .background = getDrawable(R.drawable.tab_selete)
                kv.encode("game",tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<ConstraintLayout>(R.id.cl_cv).background = null
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.tabLayout.getTabAt(kv.decodeInt("game",0))?.select()

        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<ConstraintLayout>(R.id.cl_cv)
                    .background = getDrawable(R.drawable.tab_selete2)
                LastClickRecorder.getInstance().platform =
                when(tab.position){
                    0 ->"pc"
                    1 ->"ps3"
                    2 ->"ps4"
                    3 ->"xbox360"
                    else->"xboxone"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<ConstraintLayout>(R.id.cl_cv).background = null
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.versions.text = "当前版本:${BuildConfig.VERSION_NAME}"
    }

    private fun go() {
        val names = binding.etEntry.text.toString()
        if (names.isEmpty()) {
            toast("先填个名吧")
            return
        }
      MainActivity.start(this@EntryActivity)
        kv.encode("preName",names)
    }
}