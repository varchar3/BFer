package com.lzc.bfer.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.lzc.bfer.adapter.classic.BFVClassicAdapter
import com.lzc.bfer.adapter.weapon.BFIWeaponsAdapter
import com.lzc.bfer.databinding.HomeFragmentBinding
import com.lzc.bfer.ext.toast
import com.lzc.bfer.local.LastClickRecorder
import com.lzc.bfer.model.BFVc
import com.lzc.bfer.widget.BallBounceDialog
import com.tencent.mmkv.MMKV

class HomeFragment : Fragment() {
    private var _binding :HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var bbd:BallBounceDialog
    private var cAdapter = BFVClassicAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )

        binding.fetchOther.setOnClickListener {
            requireActivity().finish()
        }

        viewModel.c.observe(viewLifecycleOwner){
            cAdapter = BFVClassicAdapter(it.classes)
            binding.recyclerView.adapter = cAdapter
        }

        MMKV.defaultMMKV().decodeString("preName")?.let {
            when (MMKV.defaultMMKV().decodeInt("game")) {
                0-> {
                    bbd = BallBounceDialog(requireContext())
                    initViewForBFV()
                    bbd.show()
                    viewModel.fetchBFVStatus(it,LastClickRecorder.getInstance().platform!!)
//                    viewModel.fetchBFVClass(it,LastClickRecorder.getInstance().platform!!)
                }
                1-> {
                    bbd = BallBounceDialog(requireContext())
                    initViewForBFIV()
                    bbd.show()
                    viewModel.fetchBFIVStatus(it,LastClickRecorder.getInstance().platform!!)
                }
                2-> {
                    bbd = BallBounceDialog(requireContext())
                    initViewForBFI()
                    bbd.show()
                    viewModel.fetchBFIStatus(it)
//                    viewModel.fetchBFIClass(it)
                }
                3-> {
                    bbd = BallBounceDialog(requireContext())
                    initViewForBFIII()
                    bbd.show()
                    viewModel.fetchBFIIIStatus(it,LastClickRecorder.getInstance().platform!!)
                }
            }
        }

        viewModel.noSuchPeople.observe(requireActivity()){
            toast(it)
            bbd.dismiss()
        }
    }

    //BF1
    private fun initViewForBFI(){
        viewModel.bfIStatus.observe(requireActivity()){
            Glide.with(this).load(it.avatar).circleCrop().into(binding.avatar)
            binding.name.text = it.userName
            binding.rank.text = it.rank
            binding.searching.text = "战地1"
            binding.playedTime.text = "${it.secondsPlayed/3600}小时"
            binding.iwKd.setNum(it.killDeath.toString(),null)
            binding.iwSpm.setNum(it.scorePerMinute.toString(),null)
            binding.iwKpm.setNum(it.killsPerMinute.toString(),null)
            binding.iwWinPercent.setNum(it.winPercent,null)
            binding.iwClasz.setNum(it.bestClass,null)
            binding.iwKill.setNum(it.kills.toString(),null)
            binding.iwHeadshot.setNum(it.headShots.toString(),null)
            binding.iwDie.setNum(it.deaths.toString(),null)
            binding.iwAccuracy.setNum(it.accuracy,null)
            binding.iwHeadshotsPresent.setNum(it.headshots,null)
            binding.iwWin.setNum(it.winPercent,null)
            binding.iwAssists.setNum(it.killAssists.toString(),null)
            binding.iwLose.setNum(it.loses.toString(),null)
            binding.iwLongestHeadShot.setNum(it.longestHeadShot.toString(),null)
            binding.iwRevives.setNum(it.revives.toString(),null)
            binding.iwDogTags.setNum(it.dogtagsTaken.toString(),null)
            binding.iwKillStreak.setNum(it.highestKillStreak.toString(),null)
            binding.iwHeal.setNum(it.heals.toString(),null)
            bbd.dismiss()
        }
    }

    //BF5
    private fun initViewForBFV(){
        viewModel.bfVStatus.observe(requireActivity()){
            Glide.with(this).load(it.avatar).circleCrop().into(binding.avatar)
            binding.name.text = it.userName
            binding.rank.text = it.rank.toString()
            binding.searching.text = "战地5"
            binding.playedTime.text = "${it.secondsPlayed/3600}小时"
            binding.iwKd.setNum(it.killDeath.toString(),null)
            binding.iwSpm.setNum(it.scorePerMinute.toString(),null)
            binding.iwKpm.setNum(it.killsPerMinute.toString(),null)
            binding.iwWinPercent.setNum(it.winPercent,null)
            binding.iwClasz.setNum(it.bestClass,null)
            binding.iwKill.setNum(it.kills.toString(),null)
            binding.iwHeadshot.setNum(it.headShots.toString(),null)
            binding.iwDie.setNum(it.deaths.toString(),null)
            binding.iwAccuracy.setNum(it.accuracy,null)
            binding.iwHeadshotsPresent.setNum(it.headshots,null)
            binding.iwWin.setNum(it.winPercent,null)
            binding.iwAssists.setNum(it.killAssists.toString(),null)
            binding.iwLose.setNum(it.loses.toString(),null)
            binding.iwLongestHeadShot.setNum(it.longestHeadShot.toString(),null)
            binding.iwRevives.setNum(it.revives.toString(),null)
            binding.iwDogTags.setNum(it.dogtagsTaken.toString(),null)
            binding.iwKillStreak.setNum(it.highestKillStreak.toString(),null)
            binding.iwHeal.setNum(it.heals.toString(),null)
            bbd.dismiss()
        }
    }

    //BF4
    private fun initViewForBFIV(){
        viewModel.bfIVStatus.observe(requireActivity()){
            Glide.with(this).load(it.avatar).circleCrop().into(binding.avatar)
            binding.name.text = it.userName
            binding.rank.text = it.rank.toString()
            binding.searching.text = "战地4"
            binding.playedTime.text = "${it.secondsPlayed/3600}小时"
            binding.iwKd.setNum(it.killDeath.toString(),null)
            binding.iwSpm.setNum(it.scorePerMinute.toString(),null)
            binding.iwKpm.setNum(it.killsPerMinute.toString(),null)
            binding.iwWinPercent.setNum(it.winPercent,null)
            binding.iwClasz.setNum(it.bestClass,null)
            binding.iwKill.setNum(it.kills.toString(),null)
            binding.iwHeadshot.setNum(it.headShots.toString(),null)
            binding.iwDie.setNum(it.deaths.toString(),null)
            binding.iwAccuracy.setNum(it.accuracy,null)
            binding.iwHeadshotsPresent.setNum(it.headshots,null)
            binding.iwWin.setNum(it.winPercent,null)
            binding.iwAssists.setNum(it.killAssists.toString(),null)
            binding.iwLose.setNum(it.loses.toString(),null)
            binding.iwLongestHeadShot.setNum(it.longestHeadShot.toString(),null)
            binding.iwRevives.setNum(it.revives.toString(),null)
//            binding.iwDogTags.setNum(it.dogtagsTaken.toString(),null)
            binding.iwDogTags.visibility = View.GONE
            binding.iwKillStreak.setNum(it.highestKillStreak.toString(),null)
            binding.iwHeal.setNum(it.heals.toString(),null)
            bbd.dismiss()
        }
    }

    //BF3
    private fun initViewForBFIII(){
        viewModel.bfIIIStatus.observe(requireActivity()){
            Glide.with(this).load(it.avatar).circleCrop().into(binding.avatar)
            binding.name.text = it.userName
            binding.rank.text = it.rank.toString()
            binding.searching.text = "战地3"
            binding.playedTime.text = "${it.secondsPlayed/3600}小时"
            binding.iwKd.setNum(it.killDeath.toString(),null)
            binding.iwSpm.setNum(it.scorePerMinute.toString(),null)
            binding.iwKpm.setNum(it.killsPerMinute.toString(),null)
            binding.iwWinPercent.setNum(it.winPercent,null)
            binding.iwClasz.setNum(it.bestClass,null)
            binding.iwKill.setNum(it.kills.toString(),null)
            binding.iwHeadshot.setNum(it.headShots.toString(),null)
            binding.iwDie.setNum(it.deaths.toString(),null)
            binding.iwAccuracy.setNum(it.accuracy,null)
            binding.iwHeadshotsPresent.setNum(it.headShots.toString(),null)
            binding.iwWin.setNum(it.winPercent,null)
            binding.iwAssists.setNum(it.killAssists.toString(),null)
            binding.iwLose.setNum(it.loses.toString(),null)
            binding.iwLongestHeadShot.setNum(it.longestHeadShot.toString(),null)
            binding.iwRevives.setNum(it.revives.toString(),null)
            binding.iwKillStreak.setNum(it.highestKillStreak.toString(),null)
            binding.iwHeal.setNum(it.heals.toString(),null)
            bbd.dismiss()
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}