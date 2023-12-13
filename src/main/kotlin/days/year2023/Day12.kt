package days.year2023

import days.Day
import kotlin.math.*

fun main() {
    println(Day12().solve())
}

class Day12 : Day(12, 2023) {

    fun solve(): Any {
        return inputList.sumOf { getPossibleArrangemenents(it) }
    }

    private fun getPossibleArrangemenents(line: String): Long {
        val (s, f) = line.split(" ")
        val springs = List(5){s}.joinToString ("?");
        val formations = List(5){f.split(",").ints()}.flatten()
        println(springs)
        println(formations)

        return 1
    }



    private fun isValidPosition(springs: String, formation: List<Long>): Boolean {
        val valid = springs.split("[.]+".toRegex())
                .filter { it.isNotEmpty() }
                .map { it.length } == formation

        return valid
    }


    fun generatePossibilities(input: String): List<String> {
        val possibilities = mutableListOf<String>()

        fun generate(current: String) {
            val index = current.indexOfFirst { it == '?' }
            if (index == -1) {
                possibilities.add(current)
            } else {
                generate(current.substring(0, index) + '.' + current.substring(index + 1))
                generate(current.substring(0, index) + '#' + current.substring(index + 1))
            }
        }
        generate(input)
        return possibilities
    }


}