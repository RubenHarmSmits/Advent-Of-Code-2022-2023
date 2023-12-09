package days.year2023

import days.Day

fun main() {
    println(Day9().solve())
}

class Day9 : Day(9, 2023) {
    fun solve(): Long {
        return inputList.map { it.split(" ").map { it.toLong() } }
                .sumOf { findNextValue(it.reversed()) }
    }

    private fun findNextValue(list: List<Long>): Long {
        val diffs = list
                .zipWithNext()
                .map { it.second - it.first }
        return if (diffs.all { it == 0L }) list.last() else list.last() + findNextValue(diffs)
    }
}
