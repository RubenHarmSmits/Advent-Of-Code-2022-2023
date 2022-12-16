package days.year2022

import days.Day
import java.lang.Integer.max


fun main() {

    val start = System.currentTimeMillis()
    println(Day16().solve())

    println("time(ms): ")
    print(System.currentTimeMillis() - start)
}

data class Valve(val name: String, val rate: Int, var tunnels: List<Any>) {
    var opened = false
}


class Day16 : Day(16) {

    var valves = mutableListOf<Valve>()

    val MAXMINUTES = 26

    var highestScore = 0;

    var maxOpened = 0;
    var maxFlow = 0;


    fun solve(): Any {
        inputList
            .forEachIndexed { i, it ->
                val inputParsed = it.split(" ")
                val valve = inputParsed[1]
                val rate = extraxtAllIntsFromString(it)[0]
                val tunnels = it.substringAfter("valves ").split(", ")
                valves.add(Valve(valve, rate, tunnels))
            }
        for (valve in valves) {
            valve.tunnels = valve.tunnels.map { name ->
                findValve(name)
            }
        }

        maxOpened = valves.filter { it.rate > 0 }.size
        maxFlow = valves.sumOf { it.rate }

        var currentValve = findValve("AA")

        checkScore(currentValve, currentValve, 1, 0, 0, mutableListOf(), false, false, currentValve, currentValve)

        return highestScore
    }

    fun checkScore(
        currentValve: Valve,
        currentElefant: Valve,
        step: Int,
        flowing: Int,
        score: Int,
        opened: MutableList<Valve>,
        moved: Boolean,
        movedE: Boolean,
        previousValve: Valve,
        previousElefant: Valve
    ) {


        var nScore = score + flowing

        val maxPossibleScore = nScore + (MAXMINUTES - step) * maxFlow

        if (maxPossibleScore <= highestScore)
        else if (opened.size == maxOpened || step == MAXMINUTES) {
            nScore += (MAXMINUTES - step) * maxFlow
            if (nScore > highestScore) {
                highestScore = nScore
                println(highestScore)
                println(flowing)
            }
        } else {
            for (tunnel in currentValve.tunnels) {
                if ((!moved || tunnel != previousValve) && (!movedE || tunnel != previousElefant)) {

                    for (tunnelE in currentElefant.tunnels) {
                        if ((!moved || tunnelE != previousValve) && (!movedE || tunnelE != previousElefant)) {
                            checkScore(
                                tunnel as Valve,
                                tunnelE as Valve,
                                step + 1,
                                flowing,
                                nScore,
                                opened.toMutableList(),
                                true,
                                true,
                                currentValve,
                                currentElefant
                            )
                        }
                    }
                    if (currentElefant.rate > 0 && !opened.contains(currentElefant)) {
                        var nopened = opened.toMutableList()

                        nopened.add(currentElefant)
                        checkScore(
                            tunnel as Valve,
                            currentElefant,
                            step + 1,
                            flowing + currentElefant.rate,
                            nScore,
                            nopened.toMutableList(),
                            true,
                            false,
                            currentValve,
                            currentElefant
                        )
                    }
                }
            }
            if (currentValve.rate > 0 && !opened.contains(currentValve)) {
                var nopened = opened.toMutableList()
                nopened.add(currentValve)

                for (tunnelE in currentElefant.tunnels) {
                    if ((!moved || tunnelE != previousValve) && (!movedE || tunnelE != previousElefant)) {
                        checkScore(
                            currentValve,
                            tunnelE as Valve,
                            step + 1,
                            flowing + currentValve.rate,
                            nScore,
                            nopened.toMutableList(),
                            false,
                            true,
                            currentValve,
                            currentElefant
                        )
                    }
                }

                if (currentElefant.rate > 0 && !nopened.contains(currentElefant)) {
                    nopened.add(currentElefant)
                    checkScore(
                        currentValve,
                        currentElefant,
                        step + 1,
                        flowing + currentElefant.rate + currentValve.rate,
                        nScore,
                        nopened.toMutableList(),
                        false,
                        false,
                        currentValve,
                        currentElefant
                    )

                }
            }

        }
    }


    fun findValve(name: Any): Valve {
        return valves.first { name == it.name }
    }


}




