package com.lzc.bfer.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.lzc.bfer.adapter.weapon.BFIIIWeaponsAdapter
import com.lzc.bfer.adapter.weapon.BFIVWeaponsAdapter
import com.lzc.bfer.adapter.weapon.BFIWeaponsAdapter
import com.lzc.bfer.adapter.weapon.BFVWeaponsAdapter
import com.lzc.bfer.config.Constants
import com.lzc.bfer.config.Constants.MMG
import com.lzc.bfer.databinding.MineFragmentBinding
import com.lzc.bfer.local.LastClickRecorder
import com.lzc.bfer.model.BFIIIWeapon
import com.lzc.bfer.model.BFIVWeapon
import com.lzc.bfer.model.BFIWeapon
import com.lzc.bfer.model.BFVWeapon
import com.lzc.bfer.util.SelectKeyWords
import com.tencent.mmkv.MMKV

class WeaponsFragment : Fragment() {
    private var _binding : MineFragmentBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<WeaponsViewModel>()

    private var bfIAdapter = BFIWeaponsAdapter(arrayListOf())//BF1
    private var bfVAdapter = BFVWeaponsAdapter(arrayListOf())//BF5
    private var bfIVAdapter = BFIVWeaponsAdapter(arrayListOf())//BF4
    private var bfIIIAdapter = BFIIIWeaponsAdapter(arrayListOf())//BF4
    private var game = MMKV.defaultMMKV().decodeInt("game")

    private val bf5TypeTab = arrayOf(
        "中机",//mmg
        "轻机",//lmg
        "冲锋",//smg
        "近战",//Melee
        "工具",//Gadget
        "配枪",//Sidearm
        "散弹",//Shotgun
        "突击",//Assault
        "半自动",//Semi-auto
        "卡宾",//Pistol carbine
        "自动",//Self-loading
        "反一切",//Anti-materiel
        "拉拴",//Bolt action carbine
    )

    private val bf5TypeName = arrayOf(
        "Mmg",//mmg
        "Lmg",//lmg
        "Smg",//smg
        "Melee",//Melee
        "Gadget",//Gadget
        "Sidearm",//Sidearm
        "Shotgun",//Shotgun
        "Assault",//Assault
        "Semi-auto",//Semi-auto
        "Pistol carbine",//Pistol carbine
        "Self-loading",//Self-loading
        "Anti-materiel",//Anti-materiel
        "Bolt action",//Bolt action carbine
    )

    private val bf1TypeTab = arrayOf(
        "轻机",//lmg
        "冲锋",//smg
        "近战",//Melee
        "步枪",//Rifle
        "工具",//Gadget
        "配枪",//Sidearm
        "榴弹",
        "标配步枪",//Stand
        "散弹",//Shotgun
        "驾驶员配枪",
        "自动",//Self-loading
        "特种",
    )

    private val bf1TypeName = arrayOf(
        "Lmg",//lmg
        "Smg",//smg
        "Melee",//Melee
        "Rifle",//Rifle
        "Gadget",//Gadget
        "Sidearm",//Sidearm
        "Grenade",
        "Stand",//标配步枪
        "Shotgun",//Shotgun
        "Tanker/p",//驾驶员配枪
        "Self-loading",//Self-loading
        "Field kit",//Field kit
    )

    companion object {
        fun newInstance() = WeaponsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MineFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        when(game){
            0-> {//BF5
                for (i in bf5TypeTab.indices){
                    val newTab = binding.tabLayout.newTab()
                    newTab.text = bf5TypeTab[i]
                    binding.tabLayout.addTab(newTab)
                }
            }
            1-> {//BF4 变态的分类,暂时不做分类
                binding.tabLayout.visibility= View.GONE
            }
            2-> {//BF1
                for (i in bf1TypeTab.indices){
                    val newTab = binding.tabLayout.newTab()
                    newTab.text = bf1TypeTab[i]
                    binding.tabLayout.addTab(newTab)
                }
            }
            3-> {//BF3 变态的分类,暂时不做分类
                binding.tabLayout.visibility= View.GONE
            }
        }

        viewModel.bf5.observe(viewLifecycleOwner){
//            bfVAdapter.setList(it as MutableList<BFVWeapon>)
            bfVAdapter = BFVWeaponsAdapter(it as MutableList<BFVWeapon>)
            binding.recyclerView.adapter = bfVAdapter
        }

        viewModel.bf1.observe(viewLifecycleOwner){
            bfIAdapter = BFIWeaponsAdapter(it as MutableList<BFIWeapon>)
            binding.recyclerView.adapter = bfIAdapter
//            bfIAdapter.setList(it as MutableList<BFIWeapon>)
        }

        viewModel.bf3.observe(viewLifecycleOwner){
            bfIIIAdapter = BFIIIWeaponsAdapter(it as MutableList<BFIIIWeapon>)
            binding.recyclerView.adapter = bfIIIAdapter
//            bfIIIAdapter.setList(it as MutableList<BFIIIWeapon>)
        }

        viewModel.bf4.observe(viewLifecycleOwner){
            bfIVAdapter = BFIVWeaponsAdapter(it as MutableList<BFIVWeapon>)
            binding.recyclerView.adapter = bfIVAdapter
//            bfIVAdapter.setList(it as MutableList<BFIVWeapon>)
        }

        binding.ivClearAll.setOnClickListener { binding.etSearch.text.clear() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when(game){
                    0-> {//BF5
                        viewModel.bf5.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                "", s.toString(),
                                LastClickRecorder.getInstance().bf5Weapons) as List<BFVWeapon>)
                    }
                    1-> {//BF4
                        viewModel.bf4.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                "", s.toString(),
                                LastClickRecorder.getInstance().bf4Weapons) as List<BFIVWeapon>)
                    }
                    2-> {//BF1
                        viewModel.bf1.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                "", s.toString(),
                                LastClickRecorder.getInstance().bf1Weapons) as List<BFIWeapon>)
                    }
                    3-> {//BF3
                        viewModel.bf3.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                "", s.toString(),
                                LastClickRecorder.getInstance().bf3Weapons) as List<BFIIIWeapon>)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(game){
                    0-> {//BF5
                        viewModel.bf5.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                bf5TypeName[tab.position], "",
                                LastClickRecorder.getInstance().bf5Weapons) as List<BFVWeapon>)
                    }
                    1-> {//BF4
                        viewModel.bf4.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                bf5TypeName[tab.position], "",
                                LastClickRecorder.getInstance().bf4Weapons) as List<BFIVWeapon>)
                    }
                    2-> {//BF1
                        viewModel.bf1.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                bf1TypeName[tab.position], "",
                                LastClickRecorder.getInstance().bf1Weapons) as List<BFIWeapon>)
                    }
                    3-> {//BF3
                        viewModel.bf3.postValue(
                            SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                                bf5TypeName[tab.position], "",
                                LastClickRecorder.getInstance().bf3Weapons) as List<BFIIIWeapon>)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        when(MMKV.defaultMMKV().decodeInt("game")){//开始全部展示MMG
            0->{//BF5
                binding.recyclerView.adapter = bfVAdapter
                viewModel.bfVWeapons.observe(requireActivity()){
                    LastClickRecorder.getInstance().bf5Weapons = it
                    bfVAdapter = BFVWeaponsAdapter(
                        SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                            MMG,"",it) as MutableList<BFVWeapon>)
                    binding.recyclerView.adapter = bfVAdapter
                }
                MMKV.defaultMMKV().decodeString("preName")?.let {
                    viewModel.getVWeaponMessage(it,LastClickRecorder.getInstance().platform!!)
                }
            }
            1->{//BF4
                binding.recyclerView.adapter = bfIVAdapter
                viewModel.bfIVWeapons.observe(requireActivity()){
                    LastClickRecorder.getInstance().bf4Weapons = it
                    bfIVAdapter = BFIVWeaponsAdapter(it.weapons)
                    binding.recyclerView.adapter = bfIVAdapter
                }
                MMKV.defaultMMKV().decodeString("preName")?.let {
                    viewModel.getIVWeaponMessage(it,LastClickRecorder.getInstance().platform!!)
                }
            }
            2->{//BF1
                binding.recyclerView.adapter = bfIAdapter
                viewModel.bfIWeapons.observe(requireActivity()){
                    LastClickRecorder.getInstance().bf1Weapons = it
                    bfIAdapter = BFIWeaponsAdapter(
                        SelectKeyWords.getInstance().fetchGunsByTypeOrName(
                            bf1TypeName[0],"",it) as MutableList<BFIWeapon>)
                    binding.recyclerView.adapter = bfIAdapter
                }
                MMKV.defaultMMKV().decodeString("preName")?.let {
                    viewModel.getIWeaponMessage(it)
                }
            }
            3->{//BF3
                binding.recyclerView.adapter = bfIIIAdapter
                viewModel.bfIIIWeapons.observe(requireActivity()){
                    LastClickRecorder.getInstance().bf3Weapons = it
                    bfIIIAdapter = BFIIIWeaponsAdapter(it.weapons)
                    binding.recyclerView.adapter = bfIIIAdapter
                }
                MMKV.defaultMMKV().decodeString("preName")?.let {
                    viewModel.getIIIWeaponMessage(it, LastClickRecorder.getInstance().platform!!)
                }
            }
        }
    }
}


