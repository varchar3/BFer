package com.lzc.bfer.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lzc.bfer.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.lzc.bfer.R
import com.lzc.bfer.databinding.ActivityMainBinding
import com.lzc.bfer.ui.fragment.HomeFragment
import com.lzc.bfer.ui.fragment.WeaponsFragment
import com.lzc.bfer.ui.fragment.VehicleFragment
import java.lang.Exception

class MainActivity : BaseActivity(){
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = MainPagerAdapter(this@MainActivity)
        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.offscreenPageLimit = 3

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.setCustomView(R.layout.tab_custom_view_home)
            when (position) {
                0 -> {
                    tab.customView!!.findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_other)
                    tab.customView!!.findViewById<TextView>(R.id.TextView).text = "面板"
                }
                1 -> {
                    tab.customView!!.findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_ak)
                    tab.customView!!.findViewById<TextView>(R.id.TextView).text = "武器"
                }
                2 -> {
                    tab.customView!!.findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_tank)
                    tab.customView!!.findViewById<TextView>(R.id.TextView).text = "载具"
                }
            }
        }.attach()

//        requestIgnoreBatteryOptimizations()//电池白名单
    }

    private fun requestIgnoreBatteryOptimizations() {
        try {
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = Uri.parse("package:" + this.packageName)
            startActivityForResult(intent, 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}

private class MainPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> WeaponsFragment.newInstance()
            2 -> VehicleFragment.newInstance()
            else -> throw IllegalStateException("No fragment can be instantiated for position $position")
        }
    }
}