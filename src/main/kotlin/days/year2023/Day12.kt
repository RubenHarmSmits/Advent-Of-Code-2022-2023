package days.year2023

import days.Day
import java.lang.Exception

fun main() {
    println(Day12().solve())
}

class Day12 : Day(12, 2023) {

    val memory = mutableMapOf<Pair<String, List<Int>>, Long>()

    fun solve(): Any {
        return inputList.sumOf { getPossibleArrangemenents(it) }
    }

    private fun getPossibleArrangemenents(line: String): Long {
        val (s, f) = line.split(" ")
        val springs = List(5) { s }.joinToString("?") + ".";
        val formations = List(5) { f.split(",").ints() }.flatten()
        return getCount(springs, formations)
    }

    fun getCount(springs: String, formations: List<Int>): Long {
        return memory.getOrPut(springs to formations) {
            getManualCount(springs, formations)
        }
    }

    private fun getManualCount(springs: String, formations: List<Int>): Long {
        if (formations.isEmpty() && springs.all { it != '#' }) {
            return 1L
        }
        try {
            var count = 0L;
            val first = springs.first()
            val sub = springs.substringBefore('.')
            val n = formations.first()
            if (first in "#?" && n <= sub.length && springs[n] != '#') {
                count += getCount(springs.substring(n + 1), formations.drop(1))
            }
            if (first in ".?") {
                count += getCount(springs.substring(1), formations)
            }
            return count

        } catch (e: Exception) {
            return 0L
        }
    }

}