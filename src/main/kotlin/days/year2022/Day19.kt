package days.year2022

import days.Day
import java.lang.Integer.max


fun main() {
    println(Day19().solve())
}

class Day19 : Day(19) {

    val MAXMINUTES = 32

    fun solve(): Any {
        return inputList
            .map { extraxtAllIntsFromString(it) }
            .map { getHighestGeode(it) }
            .product()

    }


    private fun getHighestGeode(list: List<Int>): Int {
        val (_, costOreRobotOre, costClayRobotOre, costObsRobotOre, costObsRobotClay, costGeodeRobotOre, costGeodeRobotObs) = list

        var robotState = RobotState(
            costOreRobotOre,
            costClayRobotOre,
            costObsRobotOre,
            costObsRobotClay,
            costGeodeRobotOre,
            costGeodeRobotObs
        )

        var maxGeode = 0;

        var cache = mutableSetOf<String>()


        fun findHighestGeode(robotState: RobotState, step: Int) {
            if (robotState.notPossibleAnymore(step, maxGeode)) return
            cache.add(robotState.id(step))

            if (step == MAXMINUTES) {
                maxGeode = max(robotState.geode, maxGeode)
            } else {
                val options = robotState.optionsToBuy()
                for (option in options) {
                    val newState = robotState.copy()
                    newState.simulateOneMinute()
                    newState.buyRobot(option)
                    if (cache.contains(newState.id(step + 1))) {
                        return
                    } else {
                        findHighestGeode(newState, step + 1)
                    }
                }
            }
        }

        findHighestGeode(robotState, 0)
        println(maxGeode)

        return maxGeode
    }

    data class RobotState(
        val costOreRobotOre: Int,
        val costClayRobotOre: Int,
        val costObsRobotOre: Int,
        val costObsRobotClay: Int,
        val costGeodeRobotOre: Int,
        val costGeodeRobotObs: Int,

        var oreRobots: Int = 1,
        var clayRobots: Int = 0,
        var obsidianRobots: Int = 0,
        var geodeRobots: Int = 0,

        var ore: Int = 0,
        var clay: Int = 0,
        var obsidian: Int = 0,
        var geode: Int = 0,

        ) {

        fun notPossibleAnymore(step: Int, maxSofar: Int): Boolean {
            val stepsTogo = 32 - step
            var possibleGeode = geode
            var possibleGeodeRobots = geodeRobots
            for (i in 0 until stepsTogo) {
                possibleGeodeRobots++
                possibleGeode += possibleGeodeRobots
            }
            return (possibleGeode <= maxSofar)
        }

        fun id(n: Int): String {
            return "$n-$oreRobots-$clayRobots-$obsidianRobots-$geodeRobots-$ore-$clay-$obsidian-$geode"
        }

        fun simulateOneMinute() {
            ore += oreRobots
            clay += clayRobots
            obsidian += obsidianRobots
            geode += geodeRobots
        }

        fun canBuyRobot(type: String): Boolean {
            when (type) {
                "ore" -> return ore >= costOreRobotOre
                "clay" -> return ore >= costClayRobotOre
                "obsidian" -> return ore >= costObsRobotOre && clay >= costObsRobotClay
                "geode" -> return ore >= costGeodeRobotOre && obsidian >= costGeodeRobotObs
                "nothing" -> return true
                else -> return false
            }
        }

        fun optionsToBuy(): List<String> {
            if (canBuyRobot("geode")) return mutableListOf("geode")
            return mutableListOf("obsidian", "clay", "ore", "nothing").filter { canBuyRobot(it) }
        }

        fun buyRobot(type: String) {
            when (type) {
                "ore" -> {
                    ore -= costOreRobotOre
                    oreRobots += 1
                }

                "clay" -> {
                    ore -= costClayRobotOre
                    clayRobots += 1
                }

                "obsidian" -> {
                    ore -= costObsRobotOre
                    clay -= costObsRobotClay
                    obsidianRobots += 1
                }

                "geode" -> {
                    ore -= costGeodeRobotOre
                    obsidian -= costGeodeRobotObs
                    geodeRobots += 1
                }
            }
        }
    }


}

