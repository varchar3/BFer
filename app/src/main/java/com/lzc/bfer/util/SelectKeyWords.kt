package com.lzc.bfer.util

import com.lzc.bfer.model.*

/**
- @author:  LZC
- @time:  2021/11/4
- @desc:
 */
class SelectKeyWords {
    fun<T> fetchGunsByTypeOrName(type: String, name:String, model: T): List<Any> {
        when (model) {
            is BFVWeaponsModel -> {
                val bf5 = arrayListOf<BFVWeapon>()
                if (type.isNotEmpty()){
                    for (j in model.weapons.indices) {
                        if (type.isNotEmpty() && model.weapons[j].type.contains(type)) {
                            bf5.add(model.weapons[j])
                        }else if (name.isNotEmpty() && model.weapons[j].weaponName.contains(name)){
                            bf5.add(model.weapons[j])
                        }
                    }
                }
                return bf5
            }
            is BFIWeaponsModel -> {
                val bf1 = arrayListOf<BFIWeapon>()
                for (j in model.weapons.indices) {
                    if (type.isNotEmpty() && model.weapons[j].type.contains(type)) {
                        bf1.add(model.weapons[j])
                    }else if (name.isNotEmpty() && model.weapons[j].weaponName.contains(name)){
                        bf1.add(model.weapons[j])
                    }
                }
                return bf1
            }
            is BFIVWeaponsModel -> {
                val bf4 = arrayListOf<BFIVWeapon>()
                for (j in model.weapons.indices) {
                    if (type.isNotEmpty() && model.weapons[j].type.contains(type)) {
                        bf4.add(model.weapons[j])
                    }else if (name.isNotEmpty() && model.weapons[j].weaponName.contains(name)){
                        bf4.add(model.weapons[j])
                    }
                }
                return bf4
            }
            is BFIIIWeaponsModel -> {
                val bf3 = arrayListOf<BFIIIWeapon>()
                for (j in model.weapons.indices) {
                    if (type.isNotEmpty() && model.weapons[j].type.contains(type)) {
                        bf3.add(model.weapons[j])
                    }else if (name.isNotEmpty() && model.weapons[j].weaponName.contains(name)){
                        bf3.add(model.weapons[j])
                    }
                }
                return bf3
            }
            else ->{
                return emptyList()
            }
        }
    }

    fun fetchVehicleByTypeOrName(
        type1: String,
        name: String,
        vehicleModel: AllVehicleModel): MutableList<Vehicle> {
        val vehicles = arrayListOf<Vehicle>()
        if (name.isNotEmpty()){
            for (v in vehicleModel.vehicles.indices) {
                if (vehicleModel.vehicles[v].vehicleName.contains(name)) {
                    vehicles.add(vehicleModel.vehicles[v])
                }
            }
        }else{
            for (v in vehicleModel.vehicles.indices) {
                if (vehicleModel.vehicles[v].type.contains(type1)) {
                    vehicles.add(vehicleModel.vehicles[v])
                }
            }
        }
        return vehicles
    }


        companion object {
        private var INSTANCE : SelectKeyWords? = null
        fun getInstance() = INSTANCE ?: kotlin.synchronized(SelectKeyWords::class.java) {
            INSTANCE ?: SelectKeyWords().also { INSTANCE = it }
        }
    }
}