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
import com.lzc.bfer.adapter.vehicle.BFVehicleAdapter
import com.lzc.bfer.databinding.VehicleFragmentBinding
import com.lzc.bfer.local.LastClickRecorder
import com.lzc.bfer.util.SelectKeyWords
import com.tencent.mmkv.MMKV

class VehicleFragment : Fragment() {
    private var _binding : VehicleFragmentBinding?=null
    private val binding get() = _binding!!
    private var vehiclesAdapter = BFVehicleAdapter(arrayListOf())//All
    private val viewModel by viewModels<VehicleViewModel>()
    private var game = MMKV.defaultMMKV().decodeInt("game")
    private val bf1TypeTab =
        arrayOf("坦克", "卡车", "轰炸", "战斗机","飞机","陆载", "飞船", "船", "驱逐舰", "定点武器", "巨兽", "马")
    private val bf1TypeName =
        arrayOf("tank", "truck", "omber","Fighter", "plane",  "Land vehicle",
            "Airship", "Boat", "Destroyer", "Stationary weapon", "Behemoth", "Horse")

    private val bf5TypeTab =
        arrayOf("坦克", "飞机", "运载", "定点武器")
    private val bf5TypeName =
        arrayOf("Tan", "Pla", "Trans", "Stat")

    companion object {
        fun newInstance() = VehicleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VehicleFragmentBinding.inflate(layoutInflater, container, false)
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

        binding.ivClearAll.setOnClickListener { binding.etSearch.text.clear() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.sv.postValue(
                    SelectKeyWords.getInstance().fetchVehicleByTypeOrName(
                        "", s.toString(),
                        LastClickRecorder.getInstance().vehicles!!))
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(game){
                    0-> {//BF5
                        viewModel.sv.postValue(
                            SelectKeyWords.getInstance().fetchVehicleByTypeOrName(
                                bf5TypeName[tab.position], "",
                                LastClickRecorder.getInstance().vehicles!!))
                    }
                    1-> {//BF4
                    }
                    2-> {//BF1
                        viewModel.sv.postValue(
                            SelectKeyWords.getInstance().fetchVehicleByTypeOrName(
                                bf1TypeName[tab.position], "",
                                LastClickRecorder.getInstance().vehicles!!))
                    }
                    3-> {//BF3
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewModel.sv.observe(viewLifecycleOwner){
            vehiclesAdapter = BFVehicleAdapter(it)
//            vehiclesAdapter.setNewInstance(it)
            binding.recyclerView.adapter = vehiclesAdapter
        }

        viewModel.v.observe(viewLifecycleOwner){
            LastClickRecorder.getInstance().vehicles = it
            when(game){
                0-> {//BF5
                    vehiclesAdapter = BFVehicleAdapter(SelectKeyWords.getInstance()
                        .fetchVehicleByTypeOrName("Tan","",it))
                }
                1-> {//BF4
                    vehiclesAdapter = BFVehicleAdapter(it.vehicles )
                }
                2-> {//BF1
                    vehiclesAdapter = BFVehicleAdapter(SelectKeyWords.getInstance()
                        .fetchVehicleByTypeOrName("tank","",it))
                }
                3-> {//BF3
                    vehiclesAdapter = BFVehicleAdapter(it.vehicles)
                }
            }
            binding.recyclerView.adapter = vehiclesAdapter
        }

        viewModel.getVehicle(
            MMKV.defaultMMKV().decodeString("preName")!!                                                                                                                                           ,
            LastClickRecorder.getInstance().platform?:"",game)
    }

}