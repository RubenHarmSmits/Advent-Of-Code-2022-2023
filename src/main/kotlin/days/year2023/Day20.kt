package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day20().solve())
}

class Day20 : Day(20, 2023) {
    var pushes =0L

    private var modules: MutableList<Module> = mutableListOf()
    private var pulses: MutableList<Pulse> = mutableListOf()

    fun solve(): Any {
        // found by finding the pattern of times "nd,pc,vd,tx" are on
        println(leastCommonMultiple(listOf(4019L, 3881L, 3767L, 3769L)))
        inputList
                .forEachIndexed { i, it ->
                    var (sort, desString) = it.split(" -> ");
                    var type = ModuleType.FLIP_FLOP
                    if (sort == "broadcaster") type = ModuleType.BROADCASTER
                    if (sort.startsWith("&")) type = ModuleType.CONJUNCTION
                    val name = sort.substring(1)
                    val destinations = desString.split(", ")
                    modules.add(Module(type, name, destinations, false))
                }
        modules.add(Module(ModuleType.FLIP_FLOP, "rx", listOf(), false))

        modules.forEach { module ->
            module.destinations.forEach { destination ->
                val desModule = getModuleByName(destination)
                if (desModule.type == ModuleType.CONJUNCTION) desModule.connectedInputs.add(ConnectedInput(module.name, false))
            }
        }

        while(true){
            pushes++
            pulses.add(Pulse("adva", "roadcaster", false))

            while (pulses.isNotEmpty()) {
                val pulse = pulses[0]
                // this way we check the pattern for all 4 connectedInputs of rx
                if(pulse.destination == "rx"){
                    val hf = getModuleByName("hf")
                    if(hf.connectedInputs[3].lastWasHigh) println(pushes)
                }
                pulses.removeFirst()
                val module = getModuleByName(pulse.destination) ?: continue
                when (module.type) {
                    ModuleType.BROADCASTER -> {
                        module.destinations.forEach { des ->
                            pulses.add(Pulse(module.name, des, pulse.isHigh))
                        }
                    }
                    ModuleType.FLIP_FLOP -> {
                        if (!pulse.isHigh) {
                            module.isOn = !module.isOn
                            module.destinations.forEach { des ->
                                pulses.add(Pulse(module.name, des, module.isOn))
                            }
                        }
                    }
                    ModuleType.CONJUNCTION -> {
                        module.connectedInputs.find { it.name == pulse.source }!!.lastWasHigh = pulse.isHigh
                        val sendHigh = !module.connectedInputs.all { it.lastWasHigh }
                        module.destinations.forEach { des ->
                            pulses.add(Pulse(module.name, des, sendHigh))
                        }
                    }
                }
            }
        }


        return pushes;
    }

    private fun getModuleByName(name: String): Module {
        return modules.find { it.name == name }!!
    }

    private data class Module(val type: ModuleType, val name: String, val destinations: List<String>, var isOn: Boolean, var connectedInputs: MutableList<ConnectedInput> = mutableListOf())
    private data class Pulse(val source: String, val destination: String, val isHigh: Boolean)
    private data class ConnectedInput(val name: String, var lastWasHigh: Boolean)

    private enum class ModuleType {
        BROADCASTER, FLIP_FLOP, CONJUNCTION
    }
}
