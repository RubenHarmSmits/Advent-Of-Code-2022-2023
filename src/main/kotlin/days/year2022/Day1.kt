package days.year2022

import days.Day

fun List<String>.ints(radix: Int = 10) = this.map { it.toInt(radix) }


fun main(args: Array<String>) {
    Day1().solve()
}

class Day1 : Day(1) {
    fun solve() {
        val calories = inputList
            .splitBy {  it==""}
            .map{it.ints().sum()}
            .sortedDescending()
        println("Part 1:" + calories[0])
        println("Part 2:" + calories.take(3).sum())
    }
}

